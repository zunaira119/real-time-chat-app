package com.project.chatService.controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/public")
    public String publicMessage() {
        return "Public chat endpoint";
    }

    @GetMapping("/private")
    public String privateMessage(Authentication authentication) {
        String user = authentication.getName();
        return "Hello " + user + ", this is a private chat message";
    }
}
