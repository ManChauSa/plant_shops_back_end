package plantShop.Entity.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.Entity.Seller;
import plantShop.Entity.dto.user.UserResponse;
import plantShop.common.constant.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
     Integer orderId;

     UserResponse buyer;
     Seller seller;
     List<OrderItems> orderItems;
     String couponCode;
     Double tax;
     LocalDate shipDate;
     Double total;
     OrderStatus status;
     String imageUrl;
     LocalDate createdDate;
     LocalDate updateDate;
}
