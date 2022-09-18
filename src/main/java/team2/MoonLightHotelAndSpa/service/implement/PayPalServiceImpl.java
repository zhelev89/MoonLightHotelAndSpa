package team2.MoonLightHotelAndSpa.service.implement;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.paypal.CreatedOrder;
import team2.MoonLightHotelAndSpa.service.PayPalService;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PayPalServiceImpl implements PayPalService {
    private static final String APPROVE_LINK_REL = "approve" ;
    private final PayPalHttpClient payPalHttpClient;

    public PayPalServiceImpl() {
        payPalHttpClient= new PayPalHttpClient(new PayPalEnvironment.Sandbox("AU01GSIj2ustKuEdWCZ3C9RSMbxF6Jbfm5rFp8813LTxS1YcYCM3GpJyS5gwlke11QIR9PDPGmg8YOR4","EOHGJa6oI30xmFnmUuY70oEA-c7CiDLBvdg_vndjNmi-0fj_ZkINMXOxVso09D5y26jbZyLilgkqKNC6"));
    }

    @Override
    @SneakyThrows
    public CreatedOrder createOrder(Double totalAmount, URI returnUrl) {
        final OrderRequest orderRequest = createOrderRequest(totalAmount, returnUrl);
//        orderRequest.purchaseUnits(List.of(new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(totalAmount.toString()))));
        final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        final HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
        final Order order = orderHttpResponse.result();
        LinkDescription approveUri = extractApprovalLink(order);
        return new CreatedOrder(order.id(),URI.create(approveUri.href()));
    }

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
    public void captureOrder(String orderId) {
        final OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(orderId);
        final HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
//        log.info("Order Capture Status: {}",httpResponse.result().status());
    }
}