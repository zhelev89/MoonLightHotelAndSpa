package team2.MoonLightHotelAndSpa.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.model.payPal.Order;
import team2.MoonLightHotelAndSpa.service.PayPalService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/")
public class PayPalController {

    private final PayPalService payPalService;

    public static final String LOCALHOST_URL = "http://localhost:8080/";
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {
            Payment payment = payPalService.createPayment(order.getPrice(), order.getCurrency(),
                    order.getMethod(), order.getIntent(), order.getDescription(),
                    LOCALHOST_URL + CANCEL_URL, LOCALHOST_URL + SUCCESS_URL);

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}
