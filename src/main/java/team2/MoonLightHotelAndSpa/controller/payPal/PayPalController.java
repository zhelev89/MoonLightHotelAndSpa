package team2.MoonLightHotelAndSpa.controller.payPal;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;
import team2.MoonLightHotelAndSpa.service.payPal.PayPalService;
import team2.MoonLightHotelAndSpa.service.room.RoomReservationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@AllArgsConstructor
public class PayPalController {
    private final PayPalService paypalService;
    private final RoomReservationService roomReservationService;

    @PostMapping("/pay/room")
    public String placeOrderRoom(@RequestParam long roomReservationId, HttpServletRequest request) throws IOException {
        final URI returnUrl = buildReturnUrlRoom(request, roomReservationId);
        CreatedOrder createdOrder = paypalService.createOrderRoom(roomReservationId, returnUrl);
        return "redirect:" + createdOrder.getApprovalLink();
    }

    private URI buildReturnUrlRoom(HttpServletRequest request, long roomReservationId) {
        String roomReservationIdString = "roomReservationId=" + String.valueOf(roomReservationId);
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/capture/room",
                    roomReservationIdString, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public String orderPage(Model model) {
        String orderId = "";
        model.addAttribute("orderId", orderId);
        return "order";
    }

    @GetMapping("/capture/room")
    public String captureOrderRoom(@RequestParam String token, @RequestParam String roomReservationId) {
        long roomReservationIdLong = Long.parseLong(roomReservationId);
        String orderId = "";
        orderId = token;
        paypalService.captureOrderRoom(token, roomReservationIdLong);
        return "redirect:/orders";
    }

    @PostMapping("/pay/table")
    public String placeOrderTable(@RequestParam long tableReservationId, HttpServletRequest request) throws IOException {
        final URI returnUrl = buildReturnUrlTable(request, tableReservationId);
        CreatedOrder createdOrder = paypalService.createOrderTable(tableReservationId, returnUrl);
        return "redirect:" + createdOrder.getApprovalLink();
    }

    private URI buildReturnUrlTable(HttpServletRequest request, long tableReservationId) {
        String tableReservationIdString = "tableReservationId=" + String.valueOf(tableReservationId);
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/capture/table",
                    tableReservationIdString, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/capture/table")
    public String captureOrderTable(@RequestParam String token, @RequestParam String tableReservationId) {
        long tableReservationIdLong = Long.parseLong(tableReservationId);
        String orderId = "";
        orderId = token;
        paypalService.captureOrderTable(token, tableReservationIdLong);
        return "redirect:/orders";
    }

    @PostMapping("/pay/transfer")
    public String placeOrderCarTransfer(@RequestParam long carTransferId, HttpServletRequest request) throws IOException {
        final URI returnUrl = buildReturnUrlCarTransfer(request, carTransferId);
        CreatedOrder createdOrder = paypalService.createOrderCarTransfer(carTransferId, returnUrl);
        return "redirect:" + createdOrder.getApprovalLink();
    }

    private URI buildReturnUrlCarTransfer(HttpServletRequest request, long carTransferId) {
        String carTransferIdString = "carTransferId=" + String.valueOf(carTransferId);
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/capture/transfer",
                    carTransferIdString, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/capture/transfer")
    public String captureOrderCarTransfer(@RequestParam String token, @RequestParam String carTransferId) {
        long carTransferIdLong = Long.parseLong(carTransferId);
        String orderId = "";
        orderId = token;
        paypalService.captureOrderCarTransfer(token, carTransferIdLong);
        return "redirect:/orders";
    }

    @PostMapping("/pay/screen")
    public String placeOrderScreenReservation(@RequestParam long screenReservationId, HttpServletRequest request) throws IOException {
        final URI returnUrl = buildReturnUrlScreenReservation(request, screenReservationId);
        CreatedOrder createdOrder = paypalService.createOrderScreenReservation(screenReservationId, returnUrl);
        return "redirect:" + createdOrder.getApprovalLink();
    }

    private URI buildReturnUrlScreenReservation(HttpServletRequest request, long screenReservationId) {
        String screenReservationIdString = "screenReservationId=" + String.valueOf(screenReservationId);
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/capture/screen",
                    screenReservationIdString, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/capture/screen")
    public String captureOrderScreenReservation(@RequestParam String token, @RequestParam String screenReservationId) throws IOException {
        long screenReservationIdLong = Long.parseLong(screenReservationId);
        String orderId = "";
        orderId = token;
        paypalService.captureOrderScreenReservation(token, screenReservationIdLong);
        return "redirect:/orders";
    }
}