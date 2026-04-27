package com.project.userService.controller;

import com.project.userService.entity.User;
import com.project.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.project.userService.dto.UserProfileUpdateRequest;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/exists")
    public boolean userExists(@RequestParam String email) {

        System.out.println("email " + email);
        return userService.exists(email);
    }

    @GetMapping("/profile")
    public User getProfile(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @PutMapping("/profile")
    public User updateProfile(
            @RequestParam String email,
            @RequestBody UserProfileUpdateRequest request
    ) {
        return
                userService.updateProfile(email, request);
    }

    @PutMapping(value = "/profile-picture/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateProfilePicture(
            @RequestParam String email,
            @RequestPart MultipartFile request
    ) {
        return userService.updateProfilePicture(email, request);
    }
}