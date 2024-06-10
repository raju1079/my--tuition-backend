package com.snipe.myTuitionCenter.auth.service;

import com.snipe.myTuitionCenter.auth.data.dto.LoginCredentialDTO;
import com.snipe.myTuitionCenter.auth.data.entity.LoginCredential;
import com.snipe.myTuitionCenter.auth.data.response.SuccessResponse;

public interface AuthService {

	SuccessResponse signUp(LoginCredentialDTO loginCredentialsDTO);

	SuccessResponse resetPassword(LoginCredentialDTO loginCredentialsDTO);

	SuccessResponse signIn(LoginCredentialDTO loginCredentialsDTO);

	SuccessResponse resetPasswordLink(String emailId);

	SuccessResponse changePassword(LoginCredentialDTO loginCredentialsDTO);

	LoginCredential createUserPasswordLink(String emailId, String roleName);

}
