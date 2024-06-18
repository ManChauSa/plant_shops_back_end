package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantShop.common.constant.OrderStatus;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User buyer;

    @OneToMany(mappedBy = "orderItemId", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private Date shipDate;

    private OrderStatus status;
    private Date createdDate;
    private Date updateDate;
}
