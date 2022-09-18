package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;

import java.io.IOException;
import java.net.URI;

public interface PayPalService {

    CreatedOrder createOrder(Double totalAmount, URI returnUrl) throws IOException;

    void captureOrder(String orderId);
}
