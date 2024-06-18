package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantShop.common.constant.ProductType;
import plantShop.common.constant.Status;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;
    private String description;
    private Double price;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "seller_Id")
    private User seller;

    private Integer inventoryCount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_Id")
    private Category category;

    private ProductType productType;

    private LocalDate createdDate;
    private LocalDate updateDate;

    @ManyToMany
    private List<DiscountAndOffer> discounts;

}
