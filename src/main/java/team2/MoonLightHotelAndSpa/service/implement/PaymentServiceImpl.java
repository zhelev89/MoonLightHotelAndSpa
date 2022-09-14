package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.payment.CreatedOrder;
import team2.MoonLightHotelAndSpa.service.PaymentService;

import java.net.URI;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PayPalHttpClient payPalHttpClient;

    public PayPayPaymentService(@Value("${paypal.clientId}") String clientId,
                                @Value("${paypal.clientSecret}") String clientSecret) {
        payPalHttpClient = new PayPalHttpClient(new PayPalEnvironment.Sandbox(clientId, clientSecret));
    }

    @Override
    public CreatedOrder createOrder(Double totalAmount, URI returnUrl) {
        return null;
    }
}
