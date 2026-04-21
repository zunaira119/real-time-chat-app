package com.project.chatService.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateMessage {

    private String receiver;
    private String content;
}