package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
}
