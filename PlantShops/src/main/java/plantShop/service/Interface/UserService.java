package plantShop.service.Interface;

import org.springframework.security.core.userdetails.UserDetailsService;
import plantShop.Entity.User;
import plantShop.Entity.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    User save(User newUser);

    User getCurrentUser();

    List<UserResponse> getAllUnapprovedSeller();

    void approveSeller(int id);

}
