package com.example.nagoyameshi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {
    @Value("${stripe.api-key}")
    private String stripeApiKey;
    
    @Value("${stripe.premium-plan-id}")
    private String stripePriceId;

    public String createStripeSession(HttpServletRequest httpServletRequest) {
        Stripe.apiKey = stripeApiKey;
        String priceId = stripePriceId;

        String requestUrl = new String(httpServletRequest.getRequestURL());
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                	.setPrice(priceId)
                                	.build())
        				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
        				.setSuccessUrl("/")
                        .setCancelUrl("/")
                        .build();

        try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "";
        }
    }
}
