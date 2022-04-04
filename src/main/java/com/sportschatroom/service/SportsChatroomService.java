package com.sportschatroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportschatroom.model.UserInfo;
import com.sportschatroom.repo.UserInfoRepo;

@Service
public class SportsChatroomService {

	@Autowired
	UserInfoRepo userInfoRepo;
	
	public void addUsername(UserInfo userInfo) {
		try {
			userInfoRepo.save(userInfo);
			System.out.println("Successfully stored into the database!");
		} catch (Exception E) {
			System.out.println("Error: Cannot write into the database!");
		}
	}
	
	
	public UserInfo retrieveUserInfo(String username) {
		UserInfo userInfo = new UserInfo();
		try {
			userInfo = userInfoRepo.findByUsername(username);
			System.out.println("Successfully retrieved from the database!");
		} catch (Exception e) {
			System.out.println("Error: Cannot retrieve data from the database!");
		}
		return userInfo;
	}
	
}
