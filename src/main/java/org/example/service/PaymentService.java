package org.example.service;

import com.stripe.exception.StripeException;
import lombok.Data;
import org.example.model.Order;
import org.example.response.PaymentResponse;

public interface PaymentService{
    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
