package com.sportschatroom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportschatroom.model.Bulletin;
import com.sportschatroom.model.Message;
import com.sportschatroom.model.UserInfo;

public interface BulletinRepo extends JpaRepository<Bulletin, Integer> {

	Bulletin findBySportName(String sportName);
	
}
