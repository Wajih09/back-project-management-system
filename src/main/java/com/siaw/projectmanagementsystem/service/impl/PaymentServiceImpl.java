package com.siaw.projectmanagementsystem.service.impl;

import com.siaw.projectmanagementsystem.model.PlanType;
import com.siaw.projectmanagementsystem.response.PaymentLinkResponse;
import com.siaw.projectmanagementsystem.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String apiKey;

    @Value("${stripe.api.secret}")
    private String apiSecret;

    @Override
    @Transactional
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(PlanType planType, double amount) throws StripeException {
        //https://www.youtube.com/watch?v=By4AJWgxmAs&ab_channel=CodeWithZosh
        //Stripe.apiKey = apiKey; modified using chatgbt
        Stripe.apiKey = apiSecret;
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5173/upgrade_plan/success?planType=" + planType)
                .setCancelUrl("http://localhost:5173/upgrade_plan/fail?planType=" + planType)
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd").setUnitAmount((long) (amount * 100))
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("siaw management")
                                        .build())
                                .build())
                        .build())
                .build();

        Session session = Session.create(params);
        PaymentLinkResponse res = new PaymentLinkResponse();
        res.setPaymentLinkUrl(session.getUrl());

        //StripeClient stripeClient = new StripeClient(apiKey);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
