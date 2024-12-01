package com.siaw.projectmanagementsystem.controller;

import com.siaw.projectmanagementsystem.controller.api.SubscriptionApi;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.PlanType;
import com.siaw.projectmanagementsystem.model.Subscription;
import com.siaw.projectmanagementsystem.service.AppUserService;
import com.siaw.projectmanagementsystem.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController implements SubscriptionApi {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private AppUserService appUserService;


    @Override
    public ResponseEntity<Subscription> getUserSubscription(String jwt) throws Exception {
        AppUser user = appUserService.findUserByJwt(jwt);
        Subscription subscription = subscriptionService.getUserSubscription(user.getId());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Subscription> upgradeSubscription(String jwt, PlanType planType) throws Exception {
        AppUser user = appUserService.findUserByJwt(jwt);
        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
