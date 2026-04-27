package com.project.chatService.kafka;

import com.project.chatService.event.MessageEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void consume(MessageEvent event) {

        System.out.println("📩 Message received:");
        System.out.println(event.getSender() + " -> " + event.getReceiver());
    }
}