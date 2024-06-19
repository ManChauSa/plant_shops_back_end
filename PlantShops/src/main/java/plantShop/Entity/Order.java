package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantShop.common.constant.OrderStatus;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User buyer;

    @OneToMany(mappedBy = "orderItemId", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    private String couponCode;
    private Double tax;
    private LocalDate shipDate;
    private Double total;
    private OrderStatus status;
    private LocalDate createdDate;
    private LocalDate updateDate;
}
