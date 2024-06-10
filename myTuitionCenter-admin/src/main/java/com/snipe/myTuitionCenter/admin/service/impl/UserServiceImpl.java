package com.snipe.myTuitionCenter.admin.service.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.snipe.myTuitionCenter.admin.common.CommonConstants;
import com.snipe.myTuitionCenter.admin.data.dto.ContactDetailsDTO;
import com.snipe.myTuitionCenter.admin.data.dto.UserDetailsDTO;
import com.snipe.myTuitionCenter.admin.data.entity.ContactDetails;
import com.snipe.myTuitionCenter.admin.data.entity.LoginCredential;
import com.snipe.myTuitionCenter.admin.data.entity.UserDetails;
import com.snipe.myTuitionCenter.admin.exception.UserException;
import com.snipe.myTuitionCenter.admin.repository.AuthRepository;
import com.snipe.myTuitionCenter.admin.repository.ContactDetailsRepository;
import com.snipe.myTuitionCenter.admin.repository.UserDetailsRepository;
import com.snipe.myTuitionCenter.admin.service.EmailService;
import com.snipe.myTuitionCenter.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDetailsRepository usersRepository;

	@Autowired
	private ContactDetailsRepository contactDetailsRepository;

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${mytuitioncenter.createPassword}")
	private String createPasswordUrl;
	
	@Value("${mytuitioncenter.headerKey}")
	private String headerKey;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${mytuitioncenter.passwordReset.url}")
	private String passwordResetUrl;

	@Autowired
	private EmailService emailService;
	

	@Override
	public UserDetailsDTO addUser(UserDetailsDTO userDTO) throws UserException {
		logger.debug("In UserServiceImpl, adding user to user details");
		
		String triggerEmailStatus = null;
		boolean triggerEmail = false;
		LoginCredential loginCredential;

		ContactDetails contact = contactDetailsRepository.findByEmailId(userDTO.getContactDetails().getEmailId());
		if (contact == null) {
			UserDetails userdomain = modelMapper.map(userDTO, UserDetails.class);

			// setting creation date
			userdomain.setCreationDate(LocalDateTime.now());
			userdomain.setStatus(CommonConstants.NEW.getValue());

			// setting login details
			if (null != userDTO.getLoginId()) {
			 loginCredential = authRepository.findById(userDTO.getLoginId())
						.orElseThrow(() -> new UserException(CommonConstants.LOGINEXCEPTION.getValue()));
				userdomain.setLoginCredential(loginCredential);
			}else {
				//set trigger email flag to true
				triggerEmail = true;
				triggerEmailStatus = CommonConstants.EMAIL_TRIGGERED.getValue();
				loginCredential = triggerSetPasswordCall(userDTO.getContactDetails().getEmailId(), userDTO.getRole().getRoleName());
				userdomain.setLoginCredential(loginCredential);		
			}
			usersRepository.save(userdomain);
			
			if(triggerEmail) {
				String resetUrl = passwordResetUrl + loginCredential.getResetPasswordToken() + "&email=" + loginCredential.getEmailId();
				//triggering email
				emailService.createPasswordTriggerMail(loginCredential.getEmailId(), resetUrl);
			}

			UserDetailsDTO userOutputDTO = new UserDetailsDTO();
			userOutputDTO.setUserId(userdomain.getUserId());
			userOutputDTO.setActive(userDTO.isActive());
			userOutputDTO.setStatus(CommonConstants.CREATED.getValue());
			userOutputDTO.setEmailStatus(triggerEmailStatus);
			return userOutputDTO;
		} else {
			throw new UserException(CommonConstants.USEREMAILEXIST.getValue());
		}
	}

	@Override
	public UserDetailsDTO editUser(String userId, UserDetailsDTO userDetailsDto) throws UserException {

		if (usersRepository.existsById(userId)) {
			// fetching the user details
			UserDetails userDetails = usersRepository.findById(userId).get();

			ContactDetails contact = userDetails.getContactDetails();
			userDetails = modelMapper.map(userDetailsDto, UserDetails.class);

			/*
			 * if (null != userDetailsDto.getRole()) { Roles role =
			 * rolesRepository.findByRoleName(userDetailsDto.getRole().getRoleName());
			 * userDetails.setRole(role); }
			 */
			// fetching contact record for the contact id
			ContactDetails contactdomain = contactDetailsRepository.findById(contact.getContactId()).get();

			ContactDetailsDTO contactdto = userDetailsDto.getContactDetails();
			contactdto.setContactId(contactdomain.getContactId());

			contactdomain = modelMapper.map(contactdto, ContactDetails.class);
			// persisting the record

			userDetails.setContactDetails(contactdomain);
			userDetails.setModifiedDate(LocalDateTime.now());
			userDetails.setStatus(CommonConstants.UPDATED.getValue());
			userDetails = usersRepository.save(userDetails);

			UserDetailsDTO userResponseDto = new UserDetailsDTO();
			userResponseDto.setUserId(userDetails.getUserId());
			userResponseDto.setActive(userDetails.isActive());
			userResponseDto.setStatus(CommonConstants.UPDATED.getValue());

			return userResponseDto;
		} else
			throw new UserException(CommonConstants.USERNOTFOUND.getValue());
	}

	@Override
	public UserDetailsDTO searchUserByEmailId(String emailId) throws UserException {
		logger.debug("In UserServiceImpl, searchUserByEmailId");

		// fetching contact details using mailId
		ContactDetails contactDetails = contactDetailsRepository.findByEmailId(emailId);
		if (null != contactDetails) {
			ContactDetailsDTO contactDto = modelMapper.map(contactDetails, ContactDetailsDTO.class);
			long id = contactDetails.getContactId();

			// fetching user details using contactId
			UserDetails userDetails = usersRepository.findByContactId(id);
			//String roleid = userDetails.getRole().getRoleId();

			//Roles role = rolesRepository.findById(roleid).get();
			//RolesDTO roledto = modelMapper.map(role, RolesDTO.class);

			userDetails.setContactDetails(contactDetails);
			UserDetailsDTO userDetailsDto = new UserDetailsDTO();
			//userDetailsDto.setRole(roledto);
			userDetailsDto.setUserId(userDetails.getUserId());
			userDetailsDto.setUserName(userDetails.getUserName());
			userDetailsDto.setGender(userDetails.getGender());
			userDetailsDto.setCreationDate(userDetails.getCreationDate());
			userDetailsDto.setJoiningDate(userDetails.getJoiningDate());
			userDetailsDto.setModifiedDate(userDetails.getModifiedDate());
			userDetailsDto.setActive(userDetails.isActive());
			userDetailsDto.setStatus(userDetails.getStatus());
			userDetailsDto.setContactDetails(contactDto);

			return userDetailsDto;
		} else {
			logger.error("In UserServiceImpl, User not found");
			throw new UserException(CommonConstants.EMAILDOESNOTEXIST.getValue());
		}
	}

	@Override
	public UserDetailsDTO searchUserByPhoneNo(long phoneNo) throws UserException {
		logger.debug("In UserServiceImpl, searchUserByPhoneNo");

		if (contactDetailsRepository.existsByPhoneNo(phoneNo)) {
			ContactDetails contactDetails = contactDetailsRepository.findByPhoneNo(phoneNo);

			if (null != contactDetails) {
				ContactDetailsDTO contactDto = modelMapper.map(contactDetails, ContactDetailsDTO.class);
				long id = contactDetails.getContactId();

				UserDetails userDetails = usersRepository.findByContactId(id);
				userDetails.setContactDetails(contactDetails);

				UserDetailsDTO userDetailsDto = new UserDetailsDTO();
				userDetailsDto.setUserId(userDetails.getUserId());
				userDetailsDto.setUserName(userDetails.getUserName());
				userDetailsDto.setGender(userDetails.getGender());
				userDetailsDto.setCreationDate(userDetails.getCreationDate());
				userDetailsDto.setJoiningDate(userDetails.getJoiningDate());
				userDetailsDto.setModifiedDate(userDetails.getModifiedDate());
				userDetailsDto.setActive(userDetails.isActive());
				userDetailsDto.setStatus(userDetails.getStatus());
				userDetailsDto.setContactDetails(contactDto);

				return userDetailsDto;
			}

		} else
			throw new UserException(CommonConstants.PHONENONOTEXIST.getValue());
		return null;
	}

	@Override
	public UserDetailsDTO searchUserByUserName(String userName) throws UserException {

		if (usersRepository.existsByUserName(userName)) {
			UserDetails userDetails = usersRepository.findByUserName(userName);

			UserDetailsDTO userDetailsDto = new UserDetailsDTO();
			userDetailsDto.setUserId(userDetails.getUserId());
			userDetailsDto.setUserName(userDetails.getUserName());
			userDetailsDto.setGender(userDetails.getGender());
			userDetailsDto.setCreationDate(userDetails.getCreationDate());
			userDetailsDto.setJoiningDate(userDetails.getJoiningDate());
			userDetailsDto.setModifiedDate(userDetails.getModifiedDate());
			userDetailsDto.setActive(userDetails.isActive());
			userDetailsDto.setStatus(userDetails.getStatus());

			long id = userDetails.getContactDetails().getContactId();
			ContactDetails contact = contactDetailsRepository.findById(id).get();
			ContactDetailsDTO contactdto = modelMapper.map(contact, ContactDetailsDTO.class);
			userDetailsDto.setContactDetails(contactdto);
			return userDetailsDto;

		} else
			throw new UserException(CommonConstants.USERNAMENOTEXIST.getValue());

	}

	@Override
	public UserDetailsDTO deactivateUser(String userId) throws UserException {
		UserDetails userDetails = usersRepository.findById(userId).get();
		if (null != userDetails) {
			userDetails.setActive(false);
			userDetails.setStatus(CommonConstants.DEACTIVATED.getValue());
			userDetails.setModifiedDate(LocalDateTime.now());
			userDetails = usersRepository.save(userDetails);

			UserDetailsDTO userDto = new UserDetailsDTO();
			userDto.setActive(userDetails.isActive());
			userDto.setStatus(userDetails.getStatus());
			return userDto;
		} else
			throw new UserException(CommonConstants.USERNOTFOUND.getValue());
	}

	private LoginCredential triggerSetPasswordCall(String emailId, String roleName) {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createPasswordUrl)
                .queryParam(CommonConstants.EMAIL.getValue(), emailId)
                .queryParam(CommonConstants.ROLE_NAME.getValue(), roleName);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.set(CommonConstants.X_SECURITY_TOKEN.getValue(), headerKey); 
	    HttpEntity<String> entity = new HttpEntity<>(headers);

	    ResponseEntity<LoginCredential> response = restTemplate.exchange(
	            builder.toUriString(),
	            HttpMethod.POST,
	            entity,
	            LoginCredential.class
	    );	
	    return response.getBody();
	}

}
