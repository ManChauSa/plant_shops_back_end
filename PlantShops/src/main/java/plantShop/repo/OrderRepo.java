package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
