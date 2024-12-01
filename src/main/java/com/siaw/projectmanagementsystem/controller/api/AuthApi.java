package com.siaw.projectmanagementsystem.controller.api;

import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.request.LoginRequest;
import com.siaw.projectmanagementsystem.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthApi {

    @PostMapping("/signup")
    ResponseEntity<AuthResponse> createUserHandler(@RequestBody AppUser user) throws Exception;

    @PostMapping("/signin")
    ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest);



}
