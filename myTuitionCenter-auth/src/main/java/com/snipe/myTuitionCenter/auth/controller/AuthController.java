package com.snipe.myTuitionCenter.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.myTuitionCenter.auth.data.dto.LoginCredentialDTO;
import com.snipe.myTuitionCenter.auth.data.entity.LoginCredential;
import com.snipe.myTuitionCenter.auth.data.response.SuccessResponse;
import com.snipe.myTuitionCenter.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mytuitioncenter")
@Tag(name = "Auth", description = "signup and Login for Admin/Student/Tutor")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Value("${mytuitioncenter.headerKey}")
	private String headerKey;
	
	
	@Operation(summary = "Sign-up", description = "Signup based on the database details")
	@PostMapping("/signup")
	public ResponseEntity<SuccessResponse> signUp(@RequestBody LoginCredentialDTO loginCredentialsDTO) {
		return new ResponseEntity<SuccessResponse>(authService.signUp(loginCredentialsDTO), HttpStatus.OK);
	}
	@Operation(summary = "Reset Password", description = "Reset the password through email link")
	@PostMapping("/resetPassword")
	public ResponseEntity<SuccessResponse> resetPassword(@RequestBody LoginCredentialDTO loginCredentialsDTO) {
		return new ResponseEntity<SuccessResponse>(authService.resetPassword(loginCredentialsDTO), HttpStatus.OK);
	}

	@Operation(summary = "Sign-in", description = "SignIn based on the database details")
    @PostMapping("/signin")
    public ResponseEntity<SuccessResponse> signIn(@RequestBody LoginCredentialDTO loginCredentialsDTO) {
        return new ResponseEntity<SuccessResponse>(authService.signIn(loginCredentialsDTO), HttpStatus.OK);
    }
	
	@Operation(summary = "Reset Password link", description = "trigger a call to send a email for rest password link")
	@PostMapping("/resetPasswordLink")
	public ResponseEntity<SuccessResponse> resetPasswordLink(@RequestParam String emailId) {
		return new ResponseEntity<SuccessResponse>(authService.resetPasswordLink(emailId), HttpStatus.OK);
	}
	@Operation(summary = "User Password creation link", description = "When a User created by admin, email is triggered from admin to auth")
	@PostMapping("/createPasswordLink")
	public ResponseEntity<?> createUserPasswordLink(@RequestParam String emailId,@RequestParam String roleName, HttpServletRequest request) {
		
		String securityToken = request.getHeader("X-Security-Token");

        if (securityToken == null) {
            return new ResponseEntity<>("Missing X-Security-Token", HttpStatus.BAD_REQUEST);
        }

        if (!headerKey.equals(securityToken)) {
            return new ResponseEntity<>("Invalid X-Security-Token value", HttpStatus.FORBIDDEN);
        }
		return new ResponseEntity<LoginCredential>(authService.createUserPasswordLink(emailId, roleName), HttpStatus.OK);
	}
	@Operation(summary = "Change Password", description = "Change password from profile page STUDENT/TUTOR")
	@PostMapping("/changePassword")
	public ResponseEntity<SuccessResponse> changePassword(@RequestBody LoginCredentialDTO loginCredentialsDTO) {
		return new ResponseEntity<SuccessResponse>(authService.changePassword(loginCredentialsDTO), HttpStatus.OK);
	}

}
