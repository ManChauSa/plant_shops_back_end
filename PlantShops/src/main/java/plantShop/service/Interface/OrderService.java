package plantShop.service.Interface;

import plantShop.Entity.dto.order.CreateOrderRequest;
import plantShop.Entity.dto.order.OrderResponse;
import plantShop.common.constant.OrderStatus;

import java.util.List;

public interface OrderService {

    void createOrder(CreateOrderRequest order);
    void changeOrderStatus(int orderId,OrderStatus orderStatus);
    void cancelOrder(int orderId);
    List<OrderResponse> getOrderHistory();
    OrderResponse getOrderDetails(int orderId);

}
