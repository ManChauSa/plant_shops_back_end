package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
}
