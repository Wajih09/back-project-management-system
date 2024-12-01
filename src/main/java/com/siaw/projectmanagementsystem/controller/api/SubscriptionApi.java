package com.siaw.projectmanagementsystem.controller.api;

import com.siaw.projectmanagementsystem.model.PlanType;
import com.siaw.projectmanagementsystem.model.Subscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/subscription")
public interface SubscriptionApi {

    @GetMapping("/user")
    ResponseEntity<Subscription> getUserSubscription(
            @RequestHeader("Authorization") String jwt) throws Exception;

    @PatchMapping("/upgrade")
    ResponseEntity<Subscription> upgradeSubscription(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PlanType planType) throws Exception;

}
