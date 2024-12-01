package com.siaw.projectmanagementsystem.controller;

import com.siaw.projectmanagementsystem.controller.api.UserApi;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {
//comm2
    @Autowired
    private AppUserService appUserService;

    @Override
    public ResponseEntity<AppUser> getUserProfile(String jwt) throws Exception {

        AppUser user = appUserService.findUserByJwt(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
