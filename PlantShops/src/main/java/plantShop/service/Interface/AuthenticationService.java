package plantShop.service.Interface;

import plantShop.entity.User;
import plantShop.entity.dto.user.JwtAuthenticationResponse;
import plantShop.entity.dto.user.RegisterUserRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(RegisterUserRequest request);
}
