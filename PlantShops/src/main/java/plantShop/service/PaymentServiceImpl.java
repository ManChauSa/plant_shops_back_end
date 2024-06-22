package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantShop.Entity.Payment;
import plantShop.Entity.User;
import plantShop.Entity.dto.payment.PaymentRequest;
import plantShop.Entity.dto.payment.PaymentResponse;
import plantShop.repo.PaymentRepo;
import plantShop.service.Interface.PaymentService;

import java.time.LocalDate;

import static plantShop.common.constant.PlantShopConstants.currentUserId;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    UserService userService;

    @Override
    public void addPayment(PaymentRequest payment) {
        // Todo get current user login
        User currentUser = new User();
        currentUser= userService.getCurrentUser();

        Payment newPayment = new Payment();
        newPayment.setCardNumber(payment.getCardNumber());
        newPayment.setPaymentMethod(payment.getPaymentMethod());
        newPayment.setExpirationDate(payment.getExpirationDate());
        newPayment.setCvv(payment.getCvv());
        newPayment.setLastName(payment.getLastName());
        newPayment.setFirstName(payment.getFirstName());
        newPayment.setPhoneNumber(payment.getPhoneNumber());
        newPayment.setUser(currentUser);
        newPayment.setCreatedDate(LocalDate.now());
        newPayment.setUpdateDate(LocalDate.now());

        paymentRepo.save(newPayment);
    }

    @Override
    public void updatePayment(int userId, PaymentRequest param) {
        var payment = modelMapper.map(paymentRepo.findAll().stream().filter(p->p.getUser().getUserId() == userId).findFirst(), Payment.class);
        if(payment == null) throw  new  IllegalArgumentException("Payment not found");

        payment.setCardNumber(param.getCardNumber());
        payment.setPaymentMethod(param.getPaymentMethod());
        payment.setExpirationDate(param.getExpirationDate());
        payment.setCvv(param.getCvv());
        payment.setLastName(param.getLastName());
        payment.setFirstName(param.getFirstName());
        payment.setPhoneNumber(param.getPhoneNumber());
        payment.setCreatedDate(LocalDate.now());
        payment.setUpdateDate(LocalDate.now());
        paymentRepo.save(payment);
    }

    @Override
    public PaymentResponse getPayemntByUserId() {
        // Todo get current user login
        User currentUser = userService.getCurrentUser();

        var payment = paymentRepo.findAll().stream()
                .filter(p->p.getUser().getUserId() == currentUser.getUserId()).findFirst().orElse(null);

        return payment != null ? modelMapper.map(payment, PaymentResponse.class) : null;
    }
}
