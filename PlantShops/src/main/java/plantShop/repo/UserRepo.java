package plantShop.repo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import plantShop.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
