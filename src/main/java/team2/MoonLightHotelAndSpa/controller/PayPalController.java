package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;
import team2.MoonLightHotelAndSpa.service.PayPalService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@AllArgsConstructor
public class PayPalController {
    private final PayPalService paypalService;

    @PostMapping("/pay")
    public String placeOrder(@RequestParam double totalAmount, HttpServletRequest request) throws IOException {
        final URI returnUrl = buildReturnUrl(request);
        CreatedOrder createdOrder = paypalService.createOrder(totalAmount, returnUrl);
        return "redirect:" + createdOrder.getApprovalLink();
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
    
    @GetMapping
    public String orderPage(Model model){
        String orderId = "";
        model.addAttribute("orderId",orderId);
        return "order";
    }

    @GetMapping("/capture")
    public String captureOrder(@RequestParam String token){
        //FIXME(Never Do this either put it in proper scope or in DB)
        String orderId = "";
        orderId = token;
        paypalService.captureOrder(token);
        return "redirect:/orders";
    }
}