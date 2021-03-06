package com.sportschatroom.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportschatroom.model.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long>{

	UserInfo findByUsername(String username);
	
	UserInfo findById(long id);
	
	@Transactional
	public void deleteByUsername(String username);

}