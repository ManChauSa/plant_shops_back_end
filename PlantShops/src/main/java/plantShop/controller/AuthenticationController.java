package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plantShop.entity.dto.user.JwtAuthenticationResponse;
import plantShop.entity.dto.user.RegisterUserRequest;
import plantShop.service.Interface.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody RegisterUserRequest request) {
        return authenticationService.signup(request);
    }

}
