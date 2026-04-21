package com.project.chatService.controller;

import com.project.chatService.dto.PrivateMessage;
import com.project.chatService.entity.Message;
import com.project.chatService.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
        import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/private-message")
    public void sendPrivateMessage(@Payload PrivateMessage message,
                                   Principal principal) {

        String sender = principal.getName();

        // Save message
        Message saved = chatService.saveMessage(
                sender,
                message.getReceiver(),
                message.getContent()
        );

        // Send to receiver
        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/messages",
                saved
        );

        // Send back to sender (for UI sync)
        messagingTemplate.convertAndSendToUser(
                sender,
                "/queue/messages",
                saved
        );
    }
}