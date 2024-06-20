package plantShop.service.Interface;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import plantShop.Entity.dto.account.User;

@Service
public interface UserService {
    UserDetailsService userDetailsService();
    User save(User newUser);
    User getCurrentUser();

}