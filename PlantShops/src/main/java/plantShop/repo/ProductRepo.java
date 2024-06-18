package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
