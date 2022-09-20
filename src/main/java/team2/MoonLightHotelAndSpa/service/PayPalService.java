package team2.MoonLightHotelAndSpa.service;

import lombok.SneakyThrows;
import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;

import java.io.IOException;
import java.net.URI;

public interface PayPalService {

    CreatedOrder createOrder(long reservationId, URI returnUrl) throws IOException;

    void captureOrder(String orderId, long reservationId);
}
