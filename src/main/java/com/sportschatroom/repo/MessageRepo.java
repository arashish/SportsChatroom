package com.sportschatroom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportschatroom.model.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
	
}
