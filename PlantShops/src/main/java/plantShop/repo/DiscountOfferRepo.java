package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.DiscountAndOffer;

public interface DiscountOfferRepo extends JpaRepository<DiscountAndOffer,Integer> {
}
