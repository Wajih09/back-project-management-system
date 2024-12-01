package com.siaw.projectmanagementsystem.service;

import com.siaw.projectmanagementsystem.model.PlanType;
import com.siaw.projectmanagementsystem.response.PaymentLinkResponse;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    ResponseEntity<PaymentLinkResponse> createPaymentLink(PlanType planType, double amount) throws StripeException;
}
