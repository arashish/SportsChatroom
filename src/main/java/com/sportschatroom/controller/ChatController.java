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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportschatroom.model.Message;
import com.sportschatroom.model.UserInfo;
import com.sportschatroom.model.UserLoginTracking;
import com.sportschatroom.request.UserInfoRequest;
import com.sportschatroom.response.MessageData;
import com.sportschatroom.service.SportsChatroomService;

@Controller
public class ChatController {
	
	@MessageMapping("/app/chat.send")
	@SendTo("/topic/public")
	public  Message sendMessage(@Payload final Message message)
	{
		return message;
	}
	
	@MessageMapping("/app/chat.newUser")
	@SendTo("/topic/public")
	public Message newUser(@Payload final Message message, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", message.getUsername());
		return message;
	}
	
	
	
//	@Autowired
//	SportsChatroomService sportsChatroomService;
//	
//	
//	@CrossOrigin
//	@GetMapping (path={"/"},produces = MediaType.APPLICATION_JSON_VALUE)
//	public String home() {
//		return "Welcome to SportsChatroom";
//	}
//	
//	@CrossOrigin
//	@PostMapping (path={"/addusername"},produces = MediaType.APPLICATION_JSON_VALUE)
//	public UserLoginTracking addUsername(@RequestBody UserInfoRequest userInfoRequest) throws Exception  {
//		UserInfo userInfo = sportsChatroomService.retrieveUserInfo(userInfoRequest.getUsername());
//		if (userInfo != null) {
//			throw new IllegalArgumentException("The user is already in the list.");
//		}
//		
//		userInfo = new UserInfo();
//		userInfo.setUsername(userInfoRequest.getUsername());
//		userInfo.setSportName(userInfoRequest.getSportName());
//
//		LocalDate localDate = LocalDate.now();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//		userInfo.setCreatedDate(dtf.format(localDate));
//		sportsChatroomService.addUsername(userInfo);
//		
//		System.out.println(sportsChatroomService.retrieveUserInfo(userInfoRequest.getUsername()).getId());
//		long id = sportsChatroomService.retrieveUserInfo(userInfoRequest.getUsername()).getId();
//		
//		LocalTime time = LocalTime.now();
//		
//		UserLoginTracking userLoginTracking = new UserLoginTracking();
//		userLoginTracking.setUserId(id);
//		userLoginTracking.setLoginDate(dtf.format(localDate));
//		userLoginTracking.setLoginTime(time.toString());
//
//		return userLoginTracking;
//	}
//	
//	@CrossOrigin
//	@PostMapping (path={"/storemessage"}, produces = MediaType.APPLICATION_JSON_VALUE)
//	public void storeMessage(@RequestBody Message message) {
//		LocalDate localDate = LocalDate.now();
//		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//		
//		//LocalTime time = LocalTime.now();
//		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");  
//		LocalDateTime currentTime = LocalDateTime.now();  
//		
//		//message.setMessageDate(dtf1.format(localDate));
//		//message.setMessageTime(dtf2.format(currentTime));
//		
//		sportsChatroomService.storeMessage(message);
//		
//		System.out.println("Message stored successfully");	
//	}
//	
//	@CrossOrigin
//	@GetMapping (path={"/retrievemessages"}, produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<MessageData> storeMessage() {
//		List<MessageData> messageDataList = sportsChatroomService.retrieveMessages();
//		System.out.println("Message retrieved successfully");
//		return messageDataList;	
//	}
	
	
	
	
}
