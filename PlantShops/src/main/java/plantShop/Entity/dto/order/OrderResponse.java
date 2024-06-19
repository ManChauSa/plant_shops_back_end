package plantShop.Entity.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.Entity.OrderItem;
import plantShop.Entity.Seller;
import plantShop.Entity.User;
import plantShop.common.constant.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
     Integer orderId;

     User buyer;
     Seller seller;
     List<OrderItem> orderItems;
     String couponCode;
     Double tax;
     LocalDate shipDate;
     Double total;
     OrderStatus status;
     LocalDate createdDate;
     LocalDate updateDate;
}
