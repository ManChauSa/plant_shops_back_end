package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.Product;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findTop8ByOrderByCreatedDateDesc();
}
