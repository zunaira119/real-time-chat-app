package com.project.chatService.service;

import com.project.chatService.entity.Message;
import com.project.chatService.event.MessageEvent;
import com.project.chatService.kafka.MessageProducer;
import com.project.chatService.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MessageRepository messageRepository;
    private final MessageProducer messageProducer;

    public Message saveMessage(String sender, String receiver, String content) {

        Message msg = new Message();
        msg.setSender(sender);
        msg.setReceiver(receiver);
        msg.setContent(content);
        msg.setTimestamp(LocalDateTime.now());

        Message saved = messageRepository.save(msg);

        MessageEvent event = new MessageEvent(
                sender,
                receiver,
                content,
                saved.getTimestamp().toString()
        );

        messageProducer.sendMessageEvent(event);

        return saved;
//        System.out.println("receiver " + receiver);
//
//        Boolean exists = restTemplate().getForObject(
//                "http://localhost:8082/users/exists?email=" + receiver,
//                Boolean.class
//        );
//        System.out.println("Invalid Token" + exists);
//
//        if (!exists) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,
//                    "Receiver not found"
//            );
//        }
//        Message msg = new Message();
//        msg.setSender(sender);
//        msg.setReceiver(receiver);
//        msg.setContent(content);
//        msg.setTimestamp(LocalDateTime.now());
//
//        return messageRepository.save(msg);
    }

    public List<Message> getConversation(String user1, String user2) {
        return messageRepository.findBySenderAndReceiverOrReceiverAndSender(
                user1, user2,
                user2, user1
        );
    }

    public Page<Message> getConversation(String user1, String user2, int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("timestamp").descending()
        );

        return messageRepository.findConversation(user1, user2, pageable);
    }
}
