package com.sportschatroom.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportschatroom.model.UserInfo;
import com.sportschatroom.model.UserLoginTracking;
import com.sportschatroom.request.UserInfoRequest;
import com.sportschatroom.service.SportsChatroomService;

@RestController
public class Controller {
	
	@Autowired
	SportsChatroomService sportsChatroomService;
	
	
	@CrossOrigin
	@GetMapping (path={"/"},produces = MediaType.APPLICATION_JSON_VALUE)
	public String home() {
		return "Welcome to SportsChatroom";
	}
	
	@CrossOrigin
	@PostMapping (path={"/addusername"},produces = MediaType.APPLICATION_JSON_VALUE)
	public UserLoginTracking addUsername(@RequestBody UserInfoRequest userInfoRequest) throws Exception  {
		UserInfo userInfo = sportsChatroomService.retrieveUserInfo(userInfoRequest.getUsername());
		if (userInfo != null) {
			throw new IllegalArgumentException("The user is already in the list.");
		}
		
		userInfo = new UserInfo();
		userInfo.setUsername(userInfoRequest.getUsername());
		userInfo.setSportName(userInfoRequest.getSportName());

		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		userInfo.setCreatedDate(dtf.format(localDate));
		sportsChatroomService.addUsername(userInfo);
		
		System.out.println(sportsChatroomService.retrieveUserInfo(userInfoRequest.getUsername()).getId());
		String id = Integer.toString(sportsChatroomService.retrieveUserInfo(userInfoRequest.getUsername()).getId());
		
		LocalTime time = LocalTime.now();
		
		UserLoginTracking userLoginTracking = new UserLoginTracking();
		userLoginTracking.setUserId(id);
		userLoginTracking.setLoginDate(dtf.format(localDate));
		userLoginTracking.setLoginTime(time.toString());

		return userLoginTracking;
	}
	
}
