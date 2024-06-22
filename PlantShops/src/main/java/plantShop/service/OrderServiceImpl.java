package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantShop.Entity.*;
import plantShop.Entity.dto.order.CreateOrderRequest;
import plantShop.Entity.dto.order.OrderItems;
import plantShop.Entity.dto.order.OrderResponse;
import plantShop.common.constant.OrderStatus;
import plantShop.common.constant.Role;
import plantShop.helper.ListMapper;
import plantShop.repo.*;
import plantShop.service.Interface.AddressService;
import plantShop.service.Interface.DiscountOfferService;
import plantShop.service.Interface.OrderService;
import plantShop.service.Interface.PaymentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static plantShop.common.constant.PlantShopConstants.currentUserId;
import static plantShop.common.constant.PlantShopConstants.tenPercentTax;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Autowired
    DiscountOfferService discountOfferService;
    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    PaymentService paymentService;

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Override
    public void createOrder(CreateOrderRequest order) {
        User currentUserRef = userService.getCurrentUser();
        User currentUser = userRepo.findUserByUserName(currentUserRef.getUsername());

        // cal total
        var subTotal = order.getItems().stream().mapToDouble(OrderItems::getPrice).sum();
        if(order.getCouponCode() != null){
            // find coupon percent discount
            var percent = discountOfferService.getAllDiscountOffers().stream().filter(d->d.getCode().equals(order.getCouponCode())).findFirst().get().getPercent();
            subTotal = (subTotal * percent)/100;
        }
        // cal tax
        var tax = subTotal* tenPercentTax;

        // address
        var address = addressService.getAddressByUserId();
        if(address == null){
            addressService.addAddress(order.getAddress());
        }else {
            addressService.updateAddress(currentUser.getUserId(),order.getAddress());
        }

        //payment
        var payment = paymentRepo.findAll().stream()
                .filter(p->p.getUser() != null && p.getUser().getUserId() == currentUser.getUserId()).findFirst();
        var a = paymentService.getPayemntByUserId();


        if(payment.isEmpty()){
            paymentService.addPayment(order.getPaymentInfor());
        }else {
            paymentService.updatePayment(currentUser.getUserId(), order.getPaymentInfor());
        }
        //save order
        Order newOder = new Order();

        newOder.setBuyer(currentUser);
        newOder.setCouponCode(order.getCouponCode());
        newOder.setTax(tax);
        newOder.setTotal(subTotal+tax);
        newOder.setShipDate(LocalDate.now());
        newOder.setStatus(OrderStatus.ORDERED);
        newOder.setCreatedDate(LocalDate.now());

        var newOrder = orderRepo.save(newOder);
        //save order items
        List<OrderItem> orderItems = listMapper.mapList(order.getItems(), new OrderItem());
        orderItems.forEach(oi -> {
            oi.setOrder(newOrder);
            oi.setCreatedDate(LocalDate.now());
            oi.setUpdateDate(LocalDate.now());
        });

        orderItemRepo.saveAll(orderItems);
    }

    @Override
    public void changeOrderStatus(int orderId, OrderStatus orderStatus) {
        var order = orderRepo.findById(orderId).get();
        if(order == null) throw new IllegalArgumentException("Order not found");

        order.setStatus(orderStatus);
        order.setUpdateDate(LocalDate.now());
        orderRepo.save(order);
    }

    @Override
    public void cancelOrder(int orderId) {
        var order = orderRepo.findById(orderId).get();
        if(order == null) throw new IllegalArgumentException("Order not found");

        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentUserId);

        if(currentUser.getRole() == Role.ROLE_BUYER.toString() && order.getStatus() != OrderStatus.ORDERED){
            throw new IllegalArgumentException("Order is can not cancelled");
        }
        changeOrderStatus(orderId, OrderStatus.CANCELLED);
    }

    @Override
    public List<OrderResponse> getOrderHistory() {
        List<OrderResponse> orders =listMapper.mapList(orderRepo.findAll(), new OrderResponse());
        orders.forEach(o->{
            o.setOrderItems(getOrderItemByOrderId(o.getOrderId()));
        });

        return  orders;
    }

    @Override
    public OrderResponse getOrderDetails(int orderId) {
        var orderDetail = modelMapper.map(orderRepo.findById(orderId), OrderResponse.class);
        orderDetail.setOrderItems(getOrderItemByOrderId(orderDetail.getOrderId()));
        return orderDetail;
    }

    private List<OrderItems> getOrderItemByOrderId(Integer orderId){
        List<OrderItems> result;
        var orderItems = orderItemRepo.findAll().stream().filter(oi->oi.getOrder().getOrderId().equals(orderId)).collect(Collectors.toList());
        result = listMapper.mapList(orderItems,new OrderItems());
        return  result;
    }
}
