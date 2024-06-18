package plantShop.service.Interface;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import plantShop.entity.User;


public interface UserService {

    UserDetailsService userDetailsService();
    User save(User user);
}
