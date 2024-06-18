package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantShop.common.constant.Status;

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

    @ManyToOne
    @JoinColumn(name = "seller_Id")
    private User seller;

    private Integer inventoryCount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_Id")
    private Category category;

    private Double rating;
    private String productType;

    private Date createdDate;
    private Date updateDate;

    @ManyToMany
    private List<DiscountAndOffer> discounts;

}
