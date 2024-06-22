package plantShop.service.Interface;

import plantShop.Entity.dto.account.JwtAuthenticationResponse;
import plantShop.Entity.dto.account.SignInRequest;
import plantShop.Entity.dto.account.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
