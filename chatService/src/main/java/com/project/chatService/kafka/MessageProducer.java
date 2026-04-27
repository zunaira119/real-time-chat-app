package com.project.chatService.kafka;

import com.project.chatService.event.MessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, MessageEvent> kafkaTemplate;

    private static final String TOPIC = "chat-messages";

    public void sendMessageEvent(MessageEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
