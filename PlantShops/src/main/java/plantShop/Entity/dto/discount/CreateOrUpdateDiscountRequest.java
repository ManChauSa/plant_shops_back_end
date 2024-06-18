package plantShop.Entity.dto.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateDiscountRequest {
    String discountName;
    String description;
    Double percent;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate createdDate;
    LocalDate updateDate;
}
