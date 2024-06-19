package plantShop.Entity.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.Entity.dto.address.AddressRequest;
import plantShop.Entity.dto.payment.PaymentRequest;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    List<OrderItems> listOfOrderItems;
    String couponCode;
    AddressRequest address;
    PaymentRequest paymentInfor;
}
