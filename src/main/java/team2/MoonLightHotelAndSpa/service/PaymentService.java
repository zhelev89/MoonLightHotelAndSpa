package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.payment.CreatedOrder;

import java.net.URI;

public interface PaymentService {

    CreatedOrder createOrder(Double totalAmount, URI returnUrl);
}
