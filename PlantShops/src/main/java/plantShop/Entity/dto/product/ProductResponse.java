package plantShop.Entity.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.Entity.Category;
import plantShop.Entity.DiscountAndOffer;
import plantShop.Entity.dto.image.ImageResponse;
import plantShop.Entity.dto.user.UserResponse;
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
     UserResponse seller;
     Integer inventoryCount;
     Status status;
     Category category;
     ProductType productType;
     LocalDate createdDate;
     LocalDate updateDate;
     List<ImageResponse> images;
     List<DiscountAndOffer> discounts;
}
