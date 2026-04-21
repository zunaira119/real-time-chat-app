package com.project.chatService.repository;

import com.project.chatService.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderAndReceiver(String sender, String receiver);

    List<Message> findByReceiver(String receiver);

    Message save(Message msg);
}
