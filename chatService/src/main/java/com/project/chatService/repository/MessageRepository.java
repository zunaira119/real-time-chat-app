package com.project.chatService.repository;

import com.project.chatService.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderAndReceiverOrReceiverAndSender(String sender1, String receiver1, String sender2, String receiver2);

    List<Message> findByReceiver(String receiver);

    @Query("""
                SELECT m FROM Message m
                WHERE (m.sender = :user1 AND m.receiver = :user2)
                   OR (m.sender = :user2 AND m.receiver = :user1)
                ORDER BY m.timestamp DESC
            """)
    Page<Message> findConversation(
            @Param("user1") String user1,
            @Param("user2") String user2,
            Pageable pageable
    );

}
