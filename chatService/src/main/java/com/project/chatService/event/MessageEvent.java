package com.project.chatService.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageEvent {
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;
}
