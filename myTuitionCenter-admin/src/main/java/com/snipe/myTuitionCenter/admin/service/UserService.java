package com.snipe.myTuitionCenter.admin.service;

import com.snipe.myTuitionCenter.admin.data.dto.UserDetailsDTO;
import com.snipe.myTuitionCenter.admin.exception.UserException;

public interface UserService {

	public UserDetailsDTO addUser(UserDetailsDTO userDto) throws UserException;

	public UserDetailsDTO editUser(String userId, UserDetailsDTO userDetailsDto) throws UserException;

	public UserDetailsDTO searchUserByEmailId(String emailId) throws UserException;

	public UserDetailsDTO searchUserByPhoneNo(long phoneNo) throws UserException;

	public UserDetailsDTO searchUserByUserName(String userName) throws UserException;

	public UserDetailsDTO deactivateUser(String userId) throws UserException;

}
