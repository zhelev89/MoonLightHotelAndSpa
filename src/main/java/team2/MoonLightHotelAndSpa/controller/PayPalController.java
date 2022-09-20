package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;
import team2.MoonLightHotelAndSpa.service.PayPalService;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@AllArgsConstructor
public class PayPalController {
    private final PayPalService paypalService;
    private final RoomReservationService roomReservationService;

    @PostMapping("/pay")
    public String placeOrder(@RequestParam long reservationId, HttpServletRequest request) throws IOException {
        final URI returnUrl = buildReturnUrl(request, reservationId);
        CreatedOrder createdOrder = paypalService.createOrder(reservationId, returnUrl);
        return "redirect:" + createdOrder.getApprovalLink();
    }

    private URI buildReturnUrl(HttpServletRequest request, long reservationId) {
        String reservationIdString = "reservationId=" + String.valueOf(reservationId);
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/capture",
                    reservationIdString, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
    @GetMapping
    public String orderPage(Model model){
        String orderId = "";
        model.addAttribute("orderId",orderId);
        return "order";
    }

    @GetMapping("/capture")
    public String captureOrder(@RequestParam String token,@RequestParam String reservationId){
        long reservationIdLong = Long.parseLong(reservationId);
        String orderId = "";
        orderId = token;
        System.out.println(token);
        paypalService.captureOrder(token, reservationIdLong);
        return "redirect:/orders";
    }
}