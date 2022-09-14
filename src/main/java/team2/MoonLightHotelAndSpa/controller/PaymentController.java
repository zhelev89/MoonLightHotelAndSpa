package team2.MoonLightHotelAndSpa.controller;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.model.payment.CreatedOrder;
import team2.MoonLightHotelAndSpa.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/orders")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public String placeOrder(@RequestParam Double totalAmount, HttpServletRequest request){
        final URI returnUrl = buildReturnUrl(request);
        CreatedOrder createdOrder = paymentService.createOrder(totalAmount, returnUrl);
        return "redirect:" + createdOrder.getApprovalLink();
    }

    @GetMapping("/capture")
    public String captureOrder(){
        return "";
    }

    private URI buildReturnUrl(HttpServletRequest request) {
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/orders/capture",
                    null, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
