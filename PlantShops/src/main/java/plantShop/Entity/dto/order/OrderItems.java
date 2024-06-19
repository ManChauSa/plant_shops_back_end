package plantShop.Entity.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {
     Integer productId;
     String productName;
     Integer quantities;
     Double price;
}
