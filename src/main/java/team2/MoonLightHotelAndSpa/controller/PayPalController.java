package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
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
    public String captureOrder(@RequestParam Double amount, HttpServletRequest request) throws IOException {
        URI requestUri= URI.create("");
        try {
            requestUri = URI.create(request.getRequestURL().toString());
            new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/capture",
                    null, null);
            System.out.println(requestUri);
            requestUri.toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        CreatedOrder createdOrder = paypalService.createOrder(amount,URI.create("http://yourIP:8080/capture"));

        return "redirect:"+createdOrder.getApprovalLink();

    }

    @GetMapping("/capture")
    public String captureOrder(@RequestParam String token){
        //FIXME(Never Do this either put it in proper scope or in DB)
        String orderId = token;
//       ) paymentService.captureOrder(token;
        System.out.println(orderId);
        return "redirect:/orders";
    }
}