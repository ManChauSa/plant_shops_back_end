package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;

    private String sellerName;
    private String sellerDescription;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private Boolean isVerified;
    private Date createdDate;
    private Date updateDate;
}
