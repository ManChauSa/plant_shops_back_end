package plantShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
}
