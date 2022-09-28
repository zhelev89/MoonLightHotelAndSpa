package team2.MoonLightHotelAndSpa.service;

import lombok.SneakyThrows;
import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;

import java.io.IOException;
import java.net.URI;

public interface PayPalService {
    CreatedOrder createOrderRoom(long roomReservationId, URI returnUrl) throws IOException;
    CreatedOrder createOrderTable(long tableReservationId, URI returnUrl) throws IOException;
    void captureOrderRoom(String orderId, long roomReservationId);
    void captureOrderTable(String orderId, long tableReservationId);
}
