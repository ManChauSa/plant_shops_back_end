package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;
    private Integer productId;
    private String productName;
    private Integer quantities;
    private Double total;
    private String image;
    private LocalDate createdDate;
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name="orderId")
    private Order order;

}
