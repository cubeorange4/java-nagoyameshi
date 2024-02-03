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

    public String createStripeSession(HttpServletRequest httpServletRequest) {
        Stripe.apiKey = stripeApiKey;
        String priceId = "{{price_1Oe6OWA78AZNO6DHoBpANHHM}}";

        String requestUrl = new String(httpServletRequest.getRequestURL());
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                	.setPrice(priceId)
                                	.build())
                        .build();
        				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)

        try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "";
        }
    }
}
