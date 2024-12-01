package com.siaw.projectmanagementsystem.controller;

import com.siaw.projectmanagementsystem.controller.api.PaymentApi;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.PlanType;
import com.siaw.projectmanagementsystem.response.PaymentLinkResponse;
import com.siaw.projectmanagementsystem.service.AppUserService;
import com.siaw.projectmanagementsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController implements PaymentApi {

    @Autowired
    PaymentService paymentService;

    @Autowired
    private AppUserService appUserService;

    @Override
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(String jwt, PlanType planType)
            throws Exception {
        AppUser user = appUserService.findUserByJwt(jwt);
        double amount = 9.99;
        if(planType.equals(PlanType.ANNUALLY)){
            amount *= 12;
            amount = (double) amount * 0.7;
        }
        return paymentService.createPaymentLink(planType, amount);
    }
}
