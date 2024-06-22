package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
