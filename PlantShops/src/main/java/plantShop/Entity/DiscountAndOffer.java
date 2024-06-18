package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DiscountAndOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discountOfferId;

    private String description;
    private Double percent;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private Date updateDate;

    private String code;
}
