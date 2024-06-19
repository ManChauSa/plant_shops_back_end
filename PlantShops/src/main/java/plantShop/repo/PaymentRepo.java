package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
