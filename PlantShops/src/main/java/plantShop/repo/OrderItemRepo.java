package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.OrderItem;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
    int countOrderItemByProductId(int productId);
}
