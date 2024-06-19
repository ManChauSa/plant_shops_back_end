package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
}
