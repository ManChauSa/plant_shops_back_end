package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    private String discountName;
    private String description;
    private Double percent;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createdDate;
    private LocalDate updateDate;

    private String code;
}
