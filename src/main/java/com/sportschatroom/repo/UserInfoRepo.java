package com.sportschatroom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportschatroom.model.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long>{

	UserInfo findByUsername(String username);
	
	UserInfo findById(long id);
}
