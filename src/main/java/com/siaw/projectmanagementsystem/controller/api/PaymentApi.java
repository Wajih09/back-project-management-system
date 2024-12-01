package com.siaw.projectmanagementsystem.controller.api;

import com.siaw.projectmanagementsystem.model.PlanType;
import com.siaw.projectmanagementsystem.response.PaymentLinkResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/payments")
public interface PaymentApi {

    @PostMapping("/{planType}")
    ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestHeader("Authorization") String jwt,
            @PathVariable PlanType planType) throws Exception;
}
