package com.siaw.projectmanagementsystem.service;

import com.siaw.projectmanagementsystem.model.AppUser;

import java.util.Optional;

public interface AppUserService {
///new comm
    //second comm from github
    AppUser findUserByJwt(String jwt) throws Exception;

    AppUser findUserByEmail(String email) throws Exception;

    AppUser findUserById(Long userId) throws Exception;

    AppUser updateUsersProjectSize(AppUser user, int number);
}
