package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.address.AddressResponse;
import plantShop.Entity.dto.order.ChangeStatusRequest;
import plantShop.Entity.dto.order.CreateOrderRequest;
import plantShop.Entity.dto.order.OrderResponse;
import plantShop.Entity.dto.payment.PaymentResponse;
import plantShop.common.constant.OrderStatus;
import plantShop.service.Interface.AddressService;
import plantShop.service.Interface.OrderService;
import plantShop.service.Interface.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;
    @Autowired
    private PaymentService paymentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public void addOrder(@RequestBody CreateOrderRequest order) {
        orderService.createOrder(order);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/ship")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void shipOrder(@PathVariable("id") int id){
        orderService.changeOrderStatus(id, OrderStatus.SHIPPED.name());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/delivery")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void deliveryOrder(@PathVariable("id") int id){
        orderService.changeOrderStatus(id, OrderStatus.DELIVERED.name());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasAnyRole('ROLE_BUYER','ROLE_SELLER')")
    public void cancelOrder(@PathVariable("id") int id){
        orderService.changeOrderStatus(id, OrderStatus.CANCELLED.name());
    }


    @GetMapping("/history")
    @PreAuthorize("hasAnyRole('ROLE_BUYER','ROLE_SELLER')")
    public List<OrderResponse> getOrderHistory(@RequestParam(required = false) String status) {
        return orderService.getOrderHistory(status);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderDetails(@PathVariable("id") int id) {
        return orderService.getOrderDetails(id);
    }
    @GetMapping("/address")
    public AddressResponse getAddressUserId() {
        return addressService.getAddressByUserId();
    }

    @GetMapping("/payment")
    public PaymentResponse getPaymentInfor() {
        return paymentService.getPayemntByUserId();
    }

}
