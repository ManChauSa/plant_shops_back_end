package plantShop.Entity.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.common.constant.OrderStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusRequest {
    private OrderStatus statusType;
}
