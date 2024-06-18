package plantShop.Entity.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.common.constant.ProductType;
import plantShop.common.constant.Status;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateProductRequest {

    Integer productId;

    String productName;
    String description;
    Double price;
    Integer inventoryCount;
    Status status;
    Integer categoryId;
    ProductType productType;
    LocalDate createdDate;
    LocalDate updateDate;
    String imageUrl;
    List<Integer> discountIds;
}
