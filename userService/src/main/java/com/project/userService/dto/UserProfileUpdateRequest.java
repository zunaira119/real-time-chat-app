package com.project.userService.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileUpdateRequest {

    // getters & setters
    private String name;
    private String phone;
    private String status;
    private String imageUrl; // or fileName after upload

}