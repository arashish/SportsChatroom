package com.sportschatroom.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportschatroom.model.Bulletin;
import com.sportschatroom.model.Message;
import com.sportschatroom.model.UserInfo;
import com.sportschatroom.service.SportsChatroomService;

@RestController
public class ChatController {
		
	@Autowired
	SportsChatroomService sportsChatroomService;
	
	
	@CrossOrigin
	@GetMapping (path={"/"},produces = MediaType.APPLICATION_JSON_VALUE)
	public String home() {
		return "Welcome to SportsChatroom";
	}
	
	@CrossOrigin
	@PostMapping (path={"/addusername"},produces = MediaType.APPLICATION_JSON_VALUE)
	public String addUsername(@RequestBody UserInfo userInfoRequest) throws Exception  {
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
		
		String status = "200";
		return status;
	}
	
	@CrossOrigin
	@PostMapping (path={"/findusername"},produces = MediaType.APPLICATION_JSON_VALUE)
	public String findUsername(@RequestBody UserInfo userInfoRequest) {
		UserInfo userInfo = sportsChatroomService.retrieveUserInfo(userInfoRequest.getUsername());
		if (userInfo != null) {
			return "200";
		} else {
			throw new IllegalArgumentException("The user is already in the list.");
		}
	}
	
	@CrossOrigin
	@DeleteMapping(value = {"/deleteusername/{username}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String storeMessage(@PathVariable(value = "username") String username) throws Exception{
		String status = sportsChatroomService.deleteUsername(username);
		return status;
	}
	
	@CrossOrigin
	@GetMapping (path={"/retrievebulletin"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Bulletin> retrieveBulletin() {
		System.out.println("Bulletin messages retrieved successfully");
		List<Bulletin> bulletins= sportsChatroomService.retieveBulletin();
		return bulletins;	
	}
	
	
	
	@CrossOrigin
	@PostMapping (path={"/updatebulletin"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public void storeMessage(@RequestBody Bulletin bulletin) {
		sportsChatroomService.updateBulletin(bulletin);
		System.out.println("Bulletin messages updated successfully");	
	}
	
	@CrossOrigin
	@GetMapping (path={"/retrieveusers"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserInfo> retrieveUsers() {
		List<UserInfo> userInfos = sportsChatroomService.retrieveUsers();
		System.out.println("Message retrieved successfully");
		return userInfos;	
	}
	
	
	@CrossOrigin
	@GetMapping (path={"/retrievemessages"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Message> storeMessage() {
		List<Message> messages = sportsChatroomService.retrieveMessages();
		System.out.println("Message retrieved successfully");
		return messages;	
	}
	
	@CrossOrigin
	@DeleteMapping(value = {"/deleteusers"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUsers() throws Exception{
		String status = sportsChatroomService.deleteUsers();
		return status;
	}
	
	@CrossOrigin
	@DeleteMapping(value = {"/deletemessages"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteMessages() throws Exception{
		String status = sportsChatroomService.deleteMessages();
		return status;
	}
	
}
