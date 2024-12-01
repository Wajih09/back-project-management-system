package com.siaw.projectmanagementsystem.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//https://www.youtube.com/watch?v=By4AJWgxmAs&ab_channel=CodeWithZosh
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentLinkResponse {

    private String paymentLinkUrl;
    private String paymentLinkId;
}
