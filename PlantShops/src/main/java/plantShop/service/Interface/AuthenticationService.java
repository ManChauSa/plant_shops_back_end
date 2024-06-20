package plantShop.service.Interface;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plantShop.Entity.dto.account.JwtAuthenticationResponse;
import plantShop.Entity.dto.account.SignInRequest;
import plantShop.Entity.dto.account.SignUpRequest;
import plantShop.Entity.dto.account.User;
import plantShop.common.constant.Role;
import plantShop.repo.UserRepo;


@Service
public class AuthenticationService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

//    public JwtAuthenticationResponse signup(SignUpRequest request) {
//        User user = new User(request.getFirstName(),
//                request.getLastName(),
//                request.getEmail(),
//                passwordEncoder.encode(request.getPassword()),
//                Role.ROLE_SELLER);
//
//        user = userService.save(user);
//        String jwt = jwtService.generateToken(user);
//
//        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt, user.getFirstName(), user.getLastName(), user.getUserName(), user.getRole().toString());
//        return jwtAuthenticationResponse;
//    }
//
//
//    public JwtAuthenticationResponse signin(SignInRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        var user = userRepository.findAll().stream().filter(u->u.getUserName() == request.getEmail()).findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
//        String jwt = jwtService.generateToken(modelMapper.map(user,User.class));
//        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt, user.getFirstName(), user.getLastName(), user.getUserName(), user.getRole().toString());
//        return jwtAuthenticationResponse;
//    }

}
