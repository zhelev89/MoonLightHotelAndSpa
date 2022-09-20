package team2.MoonLightHotelAndSpa.service.implement;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.service.PayPalService;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Service
public class PayPalServiceImpl implements PayPalService {
    private static final String APPROVE_LINK_REL = "approve" ;
    @Autowired
    private RoomReservationService roomReservationService;
    private final PayPalHttpClient payPalHttpClient;

    public PayPalServiceImpl() {
        payPalHttpClient= new PayPalHttpClient(new PayPalEnvironment.Sandbox("AU01GSIj2ustKuEdWCZ3C9RSMbxF6Jbfm5rFp8813LTxS1YcYCM3GpJyS5gwlke11QIR9PDPGmg8YOR4","EOHGJa6oI30xmFnmUuY70oEA-c7CiDLBvdg_vndjNmi-0fj_ZkINMXOxVso09D5y26jbZyLilgkqKNC6"));
    }

    @Override
    @SneakyThrows
    public CreatedOrder createOrder(long reservationId, URI returnUrl) throws IOException {
        RoomReservation roomReservation = roomReservationService.findById(reservationId);
        roomReservationService.isPaid(reservationId);
        double amount = (double) roomReservation.getPrice();
        final OrderRequest orderRequest = createOrderRequest(amount, returnUrl);
//        orderRequest.purchaseUnits(List.of(new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(totalAmount.toString()))));
        final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        final HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
        final Order order = orderHttpResponse.result();
        LinkDescription approveUri = extractApprovalLink(order);
        return new CreatedOrder(order.id(),URI.create(approveUri.href()));
    }

//    private OrderRequest buildCompleteRequestBody(double amount) {
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.checkoutPaymentIntent("AUTHORIZE");
//        String amountString = String.valueOf(amount);
//
//        ApplicationContext applicationContext = new ApplicationContext().brandName("EXAMPLE INC").landingPage("BILLING")
//                .cancelUrl("https://www.example.com").returnUrl("https://www.example.com").userAction("CONTINUE")
//                .shippingPreference("SET_PROVIDED_ADDRESS");
//        orderRequest.applicationContext(applicationContext);
//
//        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
//        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().referenceId("PUHF")
//                .description("Moonlight Hotel room")
//                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("BGN").value("220")
//                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("USD").value("180.00"))
//                                .shipping(new Money().currencyCode("BGN").value("20.00"))
//                                .handling(new Money().currencyCode("BGN").value("10.00"))
//                                .taxTotal(new Money().currencyCode("BGN").value("20.00"))
//                                .shippingDiscount(new Money().currencyCode("USD").value("10.00"))))
//                .items(new ArrayList<Item>() {
//                    {
//                        add(new Item().name("Moonlight Hotel room").description("Moonlight Hotel room").sku("sku01")
//                                .unitAmount(new Money().currencyCode("USD").value(amountString))
//                                .tax(new Money().currencyCode("USD").value("10.00")).quantity("1")
//                                .category("PHYSICAL_GOODS"));
//                        add(new Item().name("Shoes").description("Running, Size 10.5").sku("sku02")
//                                .unitAmount(new Money().currencyCode("USD").value("45.00"))
//                                .tax(new Money().currencyCode("USD").value("5.00")).quantity("2")
//                                .category("PHYSICAL_GOODS"));
//                    }
//                })
//                .shippingDetail(new ShippingDetail().name(new Name().fullName("John Doe"))
//                        .addressPortable(new AddressPortable().addressLine1("123 Townsend St").addressLine2("Floor 6")
//                                .adminArea2("San Francisco").adminArea1("CA").postalCode("94107").countryCode("US")));
//        purchaseUnitRequests.add(purchaseUnitRequest);
//        orderRequest.purchaseUnits(purchaseUnitRequests);
//        return orderRequest;
//    }


    private OrderRequest createOrderRequest(Double totalAmount, URI returnUrl) {
        final OrderRequest orderRequest = new OrderRequest();
        setCheckoutIntent(orderRequest);
        setPurchaseUnits(totalAmount, orderRequest);
        setApplicationContext(returnUrl, orderRequest);
        return orderRequest;
    }

    private OrderRequest setApplicationContext(URI returnUrl, OrderRequest orderRequest) {
        return orderRequest.applicationContext(new ApplicationContext().returnUrl(returnUrl.toString()));
    }

    private void setPurchaseUnits(Double totalAmount, OrderRequest orderRequest) {
        final PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(totalAmount.toString()));
        orderRequest.purchaseUnits(Arrays.asList(purchaseUnitRequest));
    }

    private void setCheckoutIntent(OrderRequest orderRequest) {
        orderRequest.checkoutPaymentIntent("CAPTURE");
    }

    private LinkDescription extractApprovalLink(Order order) {
        LinkDescription approveUri = order.links().stream()
                .filter(link -> APPROVE_LINK_REL.equals(link.rel()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return approveUri;
    }



    @Override
    @SneakyThrows
    @Transactional
    public void captureOrder(String orderId, long reservationId) {
        RoomReservation roomReservation = roomReservationService.findById(reservationId);
        roomReservation.setStatus("PAID");
        final OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(orderId);
        final HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
    }
}