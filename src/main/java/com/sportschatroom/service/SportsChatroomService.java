package com.sportschatroom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportschatroom.model.Message;
import com.sportschatroom.model.UserInfo;
import com.sportschatroom.repo.MessageRepo;
import com.sportschatroom.repo.UserInfoRepo;
import com.sportschatroom.response.MessageData;

@Service
public class SportsChatroomService {

	@Autowired
	UserInfoRepo userInfoRepo;
	
	@Autowired
	MessageRepo messageRepo;
	
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
	
	public void storeMessage(Message message) {
		try {
			messageRepo.save(message);
			System.out.println("Successfully stored into the database!");
		} catch (Exception e) {
			System.out.println("Error: Cannot write into the database!");
		}
	}
	
	public List<MessageData> retrieveMessages() {
		List<Message> messageList= new ArrayList<Message>();
		List<MessageData> messageDataList = new ArrayList<MessageData>();
		try {
			messageList = messageRepo.findAll();
			for (Message message: messageList) {
				UserInfo userInfo = userInfoRepo.findById(message.getUserId());
				MessageData messageData = new MessageData();
				messageData.setName(userInfo.getUsername());
				messageData.setMessage(message.getMessage());
				messageData.setMessageTime(message.getMessageTime());
				messageData.setMessageDate(message.getMessageDate());
				messageDataList.add(messageData);
			}
			
			System.out.println("Successfully retrieved from the database!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error: Cannot retrieve data from the database!");
		}
		return messageDataList;
	}
	
}
