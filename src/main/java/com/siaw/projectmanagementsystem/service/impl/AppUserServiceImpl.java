package com.siaw.projectmanagementsystem.service.impl;

import com.siaw.projectmanagementsystem.config.JwtProvider;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.repository.AppUserRepository;
import com.siaw.projectmanagementsystem.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser findUserByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public AppUser findUserByEmail(String email) throws Exception {
        AppUser user = appUserRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User not found !");
        }
        return user;
    }

    @Override
    public AppUser findUserById(Long userId) throws Exception {
        Optional<AppUser> user = appUserRepository.findById(userId);
        return user.orElseThrow(() -> new Exception("User not found !")); //instead of method 2h36min
    }

    @Override
    public AppUser updateUsersProjectSize(AppUser user, int number) {
        user.setProjectSize(user.getProjectSize() + number);
        return appUserRepository.save(user);
    }
}
