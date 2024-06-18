package plantShop.Entity.dto.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.Entity.Category;
import plantShop.Entity.DiscountAndOffer;
import plantShop.Entity.User;
import plantShop.common.constant.ProductType;
import plantShop.common.constant.Status;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
     Integer productId;

     String productName;
     String description;
     Double price;
     User seller;
     Integer inventoryCount;
     Status status;
     Category category;
     ProductType productType;
     LocalDate createdDate;
     LocalDate updateDate;
     String imageUrl;
     List<DiscountAndOffer> discounts;
}
