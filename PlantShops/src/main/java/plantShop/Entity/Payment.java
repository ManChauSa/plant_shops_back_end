package plantShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantShop.common.constant.PaymentMethod;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String cardNumber;
    private PaymentMethod paymentMethod;
    private String expirationDate;
    private String cvv;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private LocalDate createdDate;
    private LocalDate updateDate;
}
