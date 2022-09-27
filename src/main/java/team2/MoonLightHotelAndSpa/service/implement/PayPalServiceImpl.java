package team2.MoonLightHotelAndSpa.service.implement;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;
import team2.MoonLightHotelAndSpa.model.reservation.ReservationStatus;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.service.PayPalService;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;
import team2.MoonLightHotelAndSpa.service.TableReservationService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PayPalServiceImpl implements PayPalService {
    private static final String APPROVE_LINK_REL = "approve" ;
    @Autowired
    private RoomReservationService roomReservationService;

    @Autowired
    private TableReservationService tableReservationService;
    private final PayPalHttpClient payPalHttpClient;


    public PayPalServiceImpl() {
        payPalHttpClient= new PayPalHttpClient(new PayPalEnvironment.Sandbox("AU01GSIj2ustKuEdWCZ3C9RSMbxF6Jbfm5rFp8813LTxS1YcYCM3GpJyS5gwlke11QIR9PDPGmg8YOR4", "EOHGJa6oI30xmFnmUuY70oEA-c7CiDLBvdg_vndjNmi-0fj_ZkINMXOxVso09D5y26jbZyLilgkqKNC6"));
    }

    @Override
    @SneakyThrows
    public CreatedOrder createOrderRoom(long roomReservationId, URI returnUrl) throws IOException {
        RoomReservation roomReservation = roomReservationService.findById(roomReservationId);
        roomReservationService.isPaid(roomReservationId);
        double amount = (double) roomReservation.getPrice();
        final OrderRequest orderRequest = createOrderRequestRoom(amount, returnUrl);
        final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        final HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
        final Order order = orderHttpResponse.result();
        LinkDescription approveUri = extractApprovalLinkRoom(order);
        return new CreatedOrder(order.id(),URI.create(approveUri.href()));
    }

    private OrderRequest createOrderRequestRoom(Double totalAmount, URI returnUrl) {
        final OrderRequest orderRequest = new OrderRequest();
        setCheckoutIntentRoom(orderRequest);
        setPurchaseUnitsRoom(totalAmount, orderRequest);
        setApplicationContextRoom(returnUrl, orderRequest);
        return orderRequest;
    }

    private OrderRequest setApplicationContextRoom(URI returnUrl, OrderRequest orderRequest) {
        return orderRequest.applicationContext(new ApplicationContext().returnUrl(returnUrl.toString()));
    }

    private void setPurchaseUnitsRoom(Double totalAmount, OrderRequest orderRequest) {
        final PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(totalAmount.toString()));
        orderRequest.purchaseUnits(Arrays.asList(purchaseUnitRequest));
    }

    private void setCheckoutIntentRoom(OrderRequest orderRequest) {
        orderRequest.checkoutPaymentIntent("CAPTURE");
    }

    private LinkDescription extractApprovalLinkRoom(Order order) {
        LinkDescription approveUri = order.links().stream()
                .filter(link -> APPROVE_LINK_REL.equals(link.rel()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return approveUri;
    }

    @Override
    @SneakyThrows
    @Transactional
    public void captureOrderRoom(String orderId, long roomReservationId) {
        String status = String.valueOf(ReservationStatus.PAID);
        RoomReservation roomReservation = roomReservationService.findById(roomReservationId);
        roomReservation.setStatus(status);
        final OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(orderId);
        final HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
    }

    @Override
    @SneakyThrows
    public CreatedOrder createOrderTable(long tableReservationId, URI returnUrl) throws IOException {
        TableReservation tableReservation = tableReservationService.findById(tableReservationId);
        tableReservationService.isPaid(tableReservationId);
        double amount = (double) tableReservation.getPrice();
        final OrderRequest orderRequest = createOrderRequestTable(amount, returnUrl);
        final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        final HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
        final Order order = orderHttpResponse.result();
        LinkDescription approveUri = extractApprovalLinkTable(order);
        return new CreatedOrder(order.id(),URI.create(approveUri.href()));
    }

    private OrderRequest createOrderRequestTable(Double totalAmount, URI returnUrl) {
        final OrderRequest orderRequest = new OrderRequest();
        setCheckoutIntentTable(orderRequest);
        setPurchaseUnitsTable(totalAmount, orderRequest);
        setApplicationContextTable(returnUrl, orderRequest);
        return orderRequest;
    }

    private OrderRequest setApplicationContextTable(URI returnUrl, OrderRequest orderRequest) {
        return orderRequest.applicationContext(new ApplicationContext().returnUrl(returnUrl.toString()));
    }

    private void setPurchaseUnitsTable(Double totalAmount, OrderRequest orderRequest) {
        final PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(totalAmount.toString()));
        orderRequest.purchaseUnits(Arrays.asList(purchaseUnitRequest));
    }

    private void setCheckoutIntentTable(OrderRequest orderRequest) {
        orderRequest.checkoutPaymentIntent("CAPTURE");
    }

    private LinkDescription extractApprovalLinkTable(Order order) {
        LinkDescription approveUri = order.links().stream()
                .filter(link -> APPROVE_LINK_REL.equals(link.rel()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return approveUri;
    }

    @Override
    @SneakyThrows
    @Transactional
    public void captureOrderTable(String orderId, long tableReservationId) {
        String status = String.valueOf(ReservationStatus.PAID);
        TableReservation tableReservation = tableReservationService.findById(tableReservationId);
        tableReservation.setStatus(status);
        final OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(orderId);
        final HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
    }
}