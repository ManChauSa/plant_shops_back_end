package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
}
