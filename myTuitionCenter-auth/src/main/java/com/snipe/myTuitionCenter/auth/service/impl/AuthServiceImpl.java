package com.snipe.myTuitionCenter.auth.service.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snipe.myTuitionCenter.auth.common.CommonConstants;
import com.snipe.myTuitionCenter.auth.common.Status;
import com.snipe.myTuitionCenter.auth.data.dto.LoginCredentialDTO;
import com.snipe.myTuitionCenter.auth.data.entity.LoginCredential;
import com.snipe.myTuitionCenter.auth.data.entity.Roles;
import com.snipe.myTuitionCenter.auth.data.entity.UserDetails;
import com.snipe.myTuitionCenter.auth.data.response.SuccessResponse;
import com.snipe.myTuitionCenter.auth.exception.PasswordIncorrectException;
import com.snipe.myTuitionCenter.auth.exception.StudentNotFoundException;
import com.snipe.myTuitionCenter.auth.exception.TokenExpiredException;
import com.snipe.myTuitionCenter.auth.exception.TokenUnavailableException;
import com.snipe.myTuitionCenter.auth.exception.UserNotFoundException;
import com.snipe.myTuitionCenter.auth.repository.AuthRepository;
import com.snipe.myTuitionCenter.auth.repository.RolesRepository;
import com.snipe.myTuitionCenter.auth.repository.UserDetailsRepository;
import com.snipe.myTuitionCenter.auth.service.AuthService;
import com.snipe.myTuitionCenter.auth.service.EmailService;
import com.snipe.myTuitionCenter.auth.service.JwtService;

@Service

public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private RolesRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtService jwtService;

	@Value("${mytuitioncenter.passwordReset.url}")
	private String passwordResetUrl;

	@Autowired
	private EmailService emailService;

	@Override
	public SuccessResponse signUp(LoginCredentialDTO loginCredentialsDTO) {
		LoginCredential loginCredentials = authRepository.findByEmailId(loginCredentialsDTO.getEmailId());
		SuccessResponse response;
		if (loginCredentials == null) {
			Roles role = roleRepository.findByRoleName(loginCredentialsDTO.getRole());
			
			String encodedPassword = passwordEncoder.encode(loginCredentialsDTO.getPassword());
			LoginCredential loginCredential = modelMapper.map(loginCredentialsDTO, LoginCredential.class);
			loginCredential.setPassword(encodedPassword);
			loginCredential.setCreationDate(LocalDateTime.now().toString());
			loginCredential.setRole(role);
			
			loginCredential = authRepository.save(loginCredential);
			response = new SuccessResponse(loginCredential.getLoginId(), loginCredential.getEmailId(), null,
					loginCredential.getRole().getRoleName(), CommonConstants.REGISTER);
		} else {
			response = new SuccessResponse(loginCredentials.getLoginId(), loginCredentials.getEmailId(), null,
					loginCredentials.getRole().getRoleName(), CommonConstants.USER_ALREADY_EXSIST);
			}
		return response;
	}

	@Override
	public SuccessResponse signIn(LoginCredentialDTO loginCredentialsDTO) {

		LoginCredential loginCredential = authRepository.findByEmailId(loginCredentialsDTO.getEmailId());
		if (loginCredential != null) {
			String storedEncodedPassword = loginCredential.getPassword();
			String providedPassword = loginCredentialsDTO.getPassword();
			if (passwordEncoder.matches(providedPassword, storedEncodedPassword)) {
				String token = jwtService.generateTokenLogin(loginCredential);
				SuccessResponse response = new SuccessResponse(loginCredential.getLoginId(), loginCredential.getEmailId(), token,
						loginCredential.getRole().getRoleName(), Status.SUCCESS.toString());
				return response;
			} else {
				throw new PasswordIncorrectException(CommonConstants.PASSWORD_INCORRECT);
			}
		} else {
			throw new UserNotFoundException(CommonConstants.USER_NOT_FOUND);
		}
	}

	@Override
	public LoginCredential createUserPasswordLink(String emailId, String roleName) {
		
			Roles role = roleRepository.findByRoleName(roleName);

			LoginCredential loginCredential  = new LoginCredential();
			loginCredential.setEmailId(emailId);
			loginCredential.setCreatedBy(CommonConstants.ADMIN);
			loginCredential.setRole(role);
			loginCredential.setCreationDate(LocalDateTime.now().toString());
			String token = jwtService.generateTokenPasswordReset(loginCredential);
			loginCredential.setResetPasswordToken(token);

			loginCredential = authRepository.save(loginCredential);
			
			return loginCredential;
		
	}

	@Override
	public SuccessResponse resetPasswordLink(String emailId) {
		LoginCredential loginCredentials = authRepository.findByEmailId(emailId);
		if (loginCredentials != null) {
			String token = jwtService.generateTokenPasswordReset(loginCredentials);
			loginCredentials.setResetPasswordToken(token);

			loginCredentials = authRepository.save(loginCredentials);

			String resetUrl = passwordResetUrl + token + "&email=" + emailId;

			emailService.resetPasswordTriggerMail(loginCredentials.getEmailId(), resetUrl);
			return new SuccessResponse(loginCredentials.getLoginId(), loginCredentials.getEmailId(),
					 null, loginCredentials.getRole().getRoleName(), CommonConstants.EMAIL_SENT_SUCCESSFULLY);
			
		} else {
			throw new UserNotFoundException(CommonConstants.USER_NOT_FOUND);
		}
	}

	@Override
	public SuccessResponse resetPassword(LoginCredentialDTO loginCredentialDTO) {
		LoginCredential loginCredentials = authRepository
				.findByResetPasswordToken(loginCredentialDTO.getPasswordResetToken());
		if (loginCredentials != null) {
			if (!jwtService.validateTokenPasswordReset(loginCredentials)) {
				
				return encodePasswordUpdate(loginCredentialDTO,loginCredentials);
				
			} else {
				loginCredentials.setResetPasswordToken(null);
				loginCredentials = authRepository.save(loginCredentials);
				
				throw new TokenExpiredException(CommonConstants.TOKEN_EXPIRED);
			}
		} else {
			throw new TokenUnavailableException(CommonConstants.TOKEN_UNAVAILABLE);
		}
	
	}
	@Override
	public SuccessResponse changePassword(LoginCredentialDTO loginCredentialsDTO) {
		if((loginCredentialsDTO.getStudentId() != null && !loginCredentialsDTO.getStudentId().isEmpty())
				|| (loginCredentialsDTO.getTutorId() != null && !loginCredentialsDTO.getTutorId().isEmpty())) {

			UserDetails userDetails = null;

		    if (loginCredentialsDTO.getStudentId() != null && !loginCredentialsDTO.getStudentId().isEmpty()) {
		        userDetails = userDetailsRepository.findbyStudentId(loginCredentialsDTO.getStudentId());
		    } else if (loginCredentialsDTO.getTutorId() != null && !loginCredentialsDTO.getTutorId().isEmpty()) {
		        userDetails = userDetailsRepository.findbyTutorId(loginCredentialsDTO.getTutorId());
		    }
		    
			if(userDetails != null) {
				String login_id = userDetails.getLoginCredential().getLoginId();
				if (loginCredentialsDTO.getNewPassword() != null && !loginCredentialsDTO.getNewPassword().isEmpty()) {
					LoginCredential loginCredentials = authRepository.findByLoginId(login_id);
					if (loginCredentials != null && passwordEncoder.matches(loginCredentialsDTO.getPassword(), loginCredentials.getPassword())) {
						if (!(passwordEncoder.matches(loginCredentialsDTO.getNewPassword(), loginCredentials.getPassword()))) {
							return encodePasswordUpdate(loginCredentialsDTO,loginCredentials);
						} else {
							throw new PasswordIncorrectException(CommonConstants.PREVIOUS_PASSWORD_REPEAT); //previous password can't be given
						}
					}else {
						throw new PasswordIncorrectException(CommonConstants.PASSWORD_MISMATCH); //previous password can't be given
					}
					
				} else {
					throw new PasswordIncorrectException(CommonConstants.PASSWORD_INCORRECT); //newPassword has to be entered
				}
			}else throw new StudentNotFoundException(CommonConstants.USER_NOT_FOUND);
		}else {
			throw new StudentNotFoundException(CommonConstants.USER_NOT_FOUND);
		}
	}

	public SuccessResponse encodePasswordUpdate(LoginCredentialDTO loginCredentialsDTO, LoginCredential loginCredentials){
		String encodedPassword = passwordEncoder.encode(loginCredentialsDTO.getNewPassword());
		loginCredentials.setPassword(encodedPassword);
		loginCredentials.setResetPasswordToken(null);

		loginCredentials = authRepository.save(loginCredentials);
		return new SuccessResponse(loginCredentials.getLoginId(), loginCredentials.getEmailId(), null,
				loginCredentials.getRole().getRoleName(), CommonConstants.PASWORD_UPDATED);
			
	}
}
