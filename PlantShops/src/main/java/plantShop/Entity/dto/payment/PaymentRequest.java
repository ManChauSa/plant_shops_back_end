package plantShop.Entity.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.common.constant.PaymentMethod;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String cardNumber;
    private PaymentMethod paymentMethod;
    private String expirationDate;
    private String cvv;
    private String lastName;
    private String firstName;
    private String phoneNumber;
}
