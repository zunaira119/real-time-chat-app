package com.project.chatService.service;

import com.project.chatService.entity.Message;
import com.project.chatService.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MessageRepository messageRepository;

    public Message saveMessage(String sender, String receiver, String content) {

        Message msg = new Message();
        msg.setSender(sender);
        msg.setReceiver(receiver);
        msg.setContent(content);
        msg.setTimestamp(LocalDateTime.now());

        return messageRepository.save(msg);
    }

    public List<Message> getConversation(String user1, String user2) {

        return messageRepository.findAll().stream()
                .filter(m ->
                        (m.getSender().equals(user1) && m.getReceiver().equals(user2)) ||
                                (m.getSender().equals(user2) && m.getReceiver().equals(user1))
                )
                .toList();
    }
}
