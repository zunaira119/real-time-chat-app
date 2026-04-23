package com.project.chatService.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.project.chatService.service.ChatService;
import com.project.chatService.entity.Message;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/public")
    public String publicMessage() {
        return "Public chat endpoint";
    }

    @GetMapping("/private")
    public String privateMessage(Authentication authentication) {
        String user = authentication.getName();
        return "Hello " + user + ", this is a private chat message";
    }

    // Send message
    @PostMapping("/send")
    public Message sendMessage(@RequestParam String receiver,
                               @RequestParam String content,
                               Authentication authentication) {

        String sender = authentication.getName();

        return chatService.saveMessage(sender, receiver, content);
    }

    // Get chat history between 2 users
    @GetMapping("/history")
    public List<Message> getChatHistory(@RequestParam String user1,
                                        @RequestParam String user2) {

        return chatService.getConversation(user1, user2);
    }

    @GetMapping("/messages")
    public Page<Message> getMessages(
            @RequestParam String user1,
            @RequestParam String user2,
            @RequestParam int page,
            @RequestParam int size) {

        return chatService.getConversation(user1, user2, page, size);
    }
}
