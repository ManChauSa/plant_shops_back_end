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
import java.util.List;
import java.util.stream.Collectors;

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
    UserServiceImpl userService;

    @Autowired
    UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public void createOrder(CreateOrderRequest order) {
        User currentUser = userService.getCurrentUser();

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
        //newOder.setTax(tax);
        newOder.setTotal(order.getTotal());
        newOder.setShipDate(LocalDate.now().plusDays(3));
        newOder.setStatus(OrderStatus.ORDERED);
        newOder.setCreatedDate(LocalDate.now());

        var newOrder = orderRepo.save(newOder);
        //save order items
        List<OrderItem> orderItems = listMapper.mapList(order.getItems(), new OrderItem());
        orderItems.forEach(oi -> {
            oi.setOrder(newOrder);
            oi.setCreatedDate(LocalDate.now());
            oi.setUpdateDate(LocalDate.now());

            Product product = productRepo.findById(oi.getProductId()).get();
            if(product != null)
            {
                product.setInventoryCount(product.getInventoryCount() - oi.getQuantities());
                productRepo.save(product);
            }
        });

        orderItemRepo.saveAll(orderItems);


    }

    @Override
    public void changeOrderStatus(int orderId, String status) {
        var orderOptional = orderRepo.findById(orderId);
        if(orderOptional.isEmpty())
            throw new IllegalArgumentException("Order not found");
        Order order = orderOptional.get();
        order.setStatus(OrderStatus.valueOf(status));
        order.setUpdateDate(LocalDate.now());
        orderRepo.save(order);
    }

    @Override
    public void cancelOrder(int orderId) {
        var order = orderRepo.findById(orderId).get();
        if(order == null) throw new IllegalArgumentException("Order not found");

        // Todo get current user login
        User currentUser = userService.getCurrentUser();

        if(currentUser.getRole() == Role.ROLE_BUYER.toString() && order.getStatus() != OrderStatus.ORDERED){
            throw new IllegalArgumentException("Order is can not cancelled");
        }
        changeOrderStatus(orderId, OrderStatus.CANCELLED.toString());
    }

    @Override
    public List<OrderResponse> getOrderHistory(String status) {
        List<Order> orderList = orderRepo.findAll();
        if(status != null)
        {
            orderList = orderList.stream().filter(o -> o.getStatus() == OrderStatus.valueOf(status)).collect(Collectors.toList());
        }
        List<OrderResponse> orders =listMapper.mapList(orderList, new OrderResponse());

        //Get Current User
        User currentUser = userService.getCurrentUser();

        if(currentUser.getRole() == Role.ROLE_BUYER.name())
        {
            orders = orders.stream().filter(o -> o.getBuyer().getUserId() == currentUser.getUserId()).collect(Collectors.toList());
        }
        if(currentUser.getRole() == Role.ROLE_SELLER.name())
        {
            List<Integer> productIds = productRepo.findAll().stream().filter(o -> o.getSeller().getUserId() == currentUser.getUserId()).map(o -> o.getProductId()).collect(Collectors.toList());
            orders = orders.stream().filter(o -> o.getOrderItems().stream().anyMatch(o1 -> productIds.contains(o1.getProductId()))).collect(Collectors.toList());
        }

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
