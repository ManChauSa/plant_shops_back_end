package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.address.AddressResponse;
import plantShop.Entity.dto.order.ChangeStatusRequest;
import plantShop.Entity.dto.order.CreateOrderRequest;
import plantShop.Entity.dto.order.OrderResponse;
import plantShop.Entity.dto.payment.PaymentResponse;
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
    public void addOrder(@RequestBody CreateOrderRequest order) {
        orderService.createOrder(order);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/change")
    public void changeStatus(@PathVariable("id") int id, @RequestBody ChangeStatusRequest statusType){
        orderService.changeOrderStatus(id,statusType.getStatusType());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable("id") int id) {
        orderService.cancelOrder(id);
    }

    @GetMapping("/history")
    public List<OrderResponse> getOrderHistory() {
        return orderService.getOrderHistory();
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
