package plantShop.service.Interface;

import plantShop.Entity.dto.payment.PaymentRequest;
import plantShop.Entity.dto.payment.PaymentResponse;

public interface PaymentService {

    void addPayment(PaymentRequest payment);
    void updatePayment(int userId, PaymentRequest payment);
    PaymentResponse getPayemntByUserId();
}
