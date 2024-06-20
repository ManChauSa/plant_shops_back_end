package plantShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plantShop.Entity.User;
import plantShop.Entity.dto.account.JwtAuthenticationResponse;
import plantShop.Entity.dto.account.SignInRequest;
import plantShop.Entity.dto.account.SignUpRequest;
import plantShop.common.constant.Role;
import plantShop.repo.UserRepo;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = new User(request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole());

        user = userService.save(user);
        String jwt = jwtService.generateToken(user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt, user.getName(), user.getEmail(), user.getRole().toString());
        return jwtAuthenticationResponse;
    }


    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        User user = userRepo.findUserByUserName(request.getUserName());
        if(user == null) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        String jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt, user.getName(), user.getEmail(), user.getRole().toString());
        return jwtAuthenticationResponse;
    }

}
