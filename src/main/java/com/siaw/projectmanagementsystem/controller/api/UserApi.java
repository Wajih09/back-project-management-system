package com.siaw.projectmanagementsystem.controller.api;

import com.siaw.projectmanagementsystem.model.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users")
public interface UserApi {

    @GetMapping("/profile")
    ResponseEntity<AppUser> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception;

}
