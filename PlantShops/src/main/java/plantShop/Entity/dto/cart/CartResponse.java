package plantShop.Entity.dto.cart;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import plantShop.Entity.Product;
import plantShop.Entity.User;

import java.util.Date;

public class CartResponse {
    private Long cartId;

    private Product product;

    private String productName;
    private int quantity;
    private Date createdDate;
    private Date updateDate;
}
