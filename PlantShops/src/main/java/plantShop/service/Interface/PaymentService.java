package plantShop.service.Interface;

import plantShop.Entity.dto.payment.PaymentRequest;
import plantShop.Entity.dto.payment.PaymentResponse;

public interface PaymentService {

    Boolean addPayment(PaymentRequest payment);
    Boolean updatePayment(int userId, PaymentRequest payment);
    PaymentResponse getPayemntByUserId();
}
