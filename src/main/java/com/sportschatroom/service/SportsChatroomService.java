package com.sportschatroom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportschatroom.model.Bulletin;
import com.sportschatroom.model.Message;
import com.sportschatroom.model.UserInfo;
import com.sportschatroom.repo.BulletinRepo;
import com.sportschatroom.repo.MessageRepo;
import com.sportschatroom.repo.UserInfoRepo;
import com.sportschatroom.response.MessageData;

@Service
public class SportsChatroomService {

	@Autowired
	UserInfoRepo userInfoRepo;
	
	@Autowired
	MessageRepo messageRepo;
	
	@Autowired
	BulletinRepo bulletinRepo;
	
	public void addUsername(UserInfo userInfo) {
		try {
			userInfoRepo.save(userInfo);
			System.out.println("Successfully stored into the database!");
		} catch (Exception E) {
			System.out.println("Error: Cannot write into the database!");
		}
	}
	
	public String deleteUsername(String username) {
		try {
			userInfoRepo.deleteByUsername(username);
			return "200";
		} catch (Exception E) {
			System.out.println("Error: Cannot delete from the database!");
			return "Error";
		}
	}
	
	public String updateBulletin(Bulletin bulletin) {
		try {
			Bulletin findBulletin = bulletinRepo.findBySportName(bulletin.getSportName());
			if (findBulletin != null) {
				bulletinRepo.save(bulletin); //existing
			} else {
				bulletinRepo.save(bulletin); //new
			}
			return "200";
		} catch (Exception E) {
			System.out.println("Error: Cannot update to the database!");
			return "Error";
		}
	}
	
	public List<Bulletin> retieveBulletin() {
		List<Bulletin> findBulletins = null;
		try {
			findBulletins = bulletinRepo.findAll();
		} catch (Exception E) {
			System.out.println("Error: Cannot update to the database!");
		}
		return findBulletins;
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
	
	public void storeMessage(Message message) {
		try {
			messageRepo.save(message);
			System.out.println("Successfully stored into the database!");
		} catch (Exception e) {
			System.out.println("Error: Cannot write into the database!");
		}
	}
	
	public List<UserInfo> retrieveUsers() {
		List<UserInfo> userInfos= new ArrayList<UserInfo>();
		try {
			userInfos = userInfoRepo.findAll();
			System.out.println("Successfully retrieved from the database!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error: Cannot retrieve data from the database!");
		}
		return userInfos;
	}
	
	public List<Message> retrieveMessages() {
		List<Message> messages= new ArrayList<Message>();
		try {
			messages = messageRepo.findAll();
			System.out.println("Successfully retrieved from the database!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error: Cannot retrieve data from the database!");
		}
		return messages;
	}
	
	public String deleteUsers() {
		try {
			userInfoRepo.deleteAll();
			return "200";
		} catch (Exception E) {
			System.out.println("Error: Cannot delete from the database!");
			return "Error";
		}
	}
	
	public String deleteMessages() {
		try {
			messageRepo.deleteAll();
			return "200";
		} catch (Exception E) {
			System.out.println("Error: Cannot delete from the database!");
			return "Error";
		}
	}
	
	
	
}
