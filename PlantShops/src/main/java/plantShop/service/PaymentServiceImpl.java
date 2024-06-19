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

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PaymentRepo paymentRepo;

    @Override
    public Boolean addPayment(PaymentRequest payment) {
        var currentuser= new User();
        currentuser.setUserId(3);

        Payment newPayment = new Payment();
        newPayment.setCardNumber(payment.getCardNumber());
        newPayment.setPaymentMethod(payment.getPaymentMethod());
        newPayment.setExpirationDate(payment.getExpirationDate());
        newPayment.setCvv(payment.getCvv());
        newPayment.setLastName(payment.getLastName());
        newPayment.setFirstName(payment.getFirstName());
        newPayment.setPhoneNumber(payment.getPhoneNumber());
        newPayment.setUser(currentuser);
        newPayment.setCreatedDate(LocalDate.now());
        newPayment.setUpdateDate(LocalDate.now());

        paymentRepo.save(newPayment);
        return true;
    }

    @Override
    public Boolean updatePayment(int userId, PaymentRequest param) {
        var payment = modelMapper.map(paymentRepo.findById(userId), Payment.class);
        if(payment == null) throw  new  IllegalArgumentException("Payment not found");

        var currentuser= new User();
        currentuser.setUserId(3);

        payment.setCardNumber(param.getCardNumber());
        payment.setPaymentMethod(param.getPaymentMethod());
        payment.setExpirationDate(param.getExpirationDate());
        payment.setCvv(param.getCvv());
        payment.setLastName(param.getLastName());
        payment.setFirstName(param.getFirstName());
        payment.setPhoneNumber(param.getPhoneNumber());
        payment.setUser(currentuser);
        payment.setCreatedDate(LocalDate.now());
        payment.setUpdateDate(LocalDate.now());
        paymentRepo.save(payment);
        return true;
    }

    @Override
    public PaymentResponse getPayemntByUserId() {
        var currentuser= new User();
        currentuser.setUserId(3);
        var payment = paymentRepo.findAll().stream()
                .filter(p->p.getUser().getUserId() == currentuser.getUserId()).findFirst().orElse(null);

        return payment != null ? modelMapper.map(payment, PaymentResponse.class) : null;
    }
}
