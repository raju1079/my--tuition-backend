package com.snipe.myTuitionCenter.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.myTuitionCenter.admin.common.CommonConstants;
import com.snipe.myTuitionCenter.admin.data.dto.UserDetailsDTO;
import com.snipe.myTuitionCenter.admin.exception.UserException;
import com.snipe.myTuitionCenter.admin.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mytuitioncenter/admin")
@Tag(name = "Admin", description = "User management APIs")
public class UserDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

	@Autowired
	UserService userService;

	/**
	 * @author Shobana V B
	 * @throws UserException
	 */

	@Operation(summary = "Add new User", description = "Adding a new user to the Database")
	@PostMapping("/user")
	public ResponseEntity<UserDetailsDTO> addUser(@RequestBody UserDetailsDTO userDetailsDTO) throws UserException {
		try {
			return new ResponseEntity<UserDetailsDTO>(userService.addUser(userDetailsDTO), HttpStatus.CREATED);
		} catch (UserException e) {
			logger.error("Please provide valid information", e.getMessage());
			throw new UserException(CommonConstants.VALIDINFORMATION.getValue());
		}
	}

	@Operation(summary = "Edit a specific user", description = "Edit details of an user")
	@PutMapping("/editUser/{userId}")
	public ResponseEntity<UserDetailsDTO> editUser(@PathVariable String userId,
			@RequestBody UserDetailsDTO userDetailsDto) throws UserException {
		try {
			return new ResponseEntity<UserDetailsDTO>(userService.editUser(userId, userDetailsDto), HttpStatus.OK);
		} catch (Exception e) {
			throw new UserException(CommonConstants.VALIDINFORMATION.getValue());
		}
	}

	@Operation(summary = "Search a user by emailId", description = "Search details of an user using EmailId")
	@GetMapping("/searchUser/byemail/{emailId}")
	public ResponseEntity<UserDetailsDTO> searchUserByEmailId(@PathVariable String emailId) throws UserException {
		try {
			return new ResponseEntity<UserDetailsDTO>(userService.searchUserByEmailId(emailId), HttpStatus.FOUND);
		} catch (UserException e) {
			logger.error("User Not Found");
			throw new UserException(CommonConstants.EMAILDOESNOTEXIST.getValue());
		}
	}

	@Operation(summary = "Search a user by phoneNo", description = "Search details of an user using Phone Number")
	@GetMapping("/searchUser/byphone/{phoneNo}")
	public ResponseEntity<UserDetailsDTO> searchUserByPhoneNo(@PathVariable long phoneNo) throws UserException {
		try {
			return new ResponseEntity<UserDetailsDTO>(userService.searchUserByPhoneNo(phoneNo), HttpStatus.FOUND);
		} catch (UserException e) {
			logger.error("User Not Found");
			throw new UserException(CommonConstants.PHONENONOTEXIST.getValue());
		}
	}

	@Operation(summary = "Delete a user", description = "Deactivate a user (make inactive)")
	@PutMapping("/deactivateUser/{userId}")
	public ResponseEntity<UserDetailsDTO> deactivateUser(@PathVariable String userId) throws UserException {
		try {
			return new ResponseEntity<UserDetailsDTO>(userService.deactivateUser(userId), HttpStatus.OK);
		} catch (UserException e) {
			throw new UserException(CommonConstants.USERNOTFOUND.getValue());
		}
	}

}
