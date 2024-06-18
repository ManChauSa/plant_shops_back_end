package plantShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import plantShop.common.constant.Role;
import plantShop.entity.User;
import plantShop.entity.dto.user.JwtAuthenticationResponse;
import plantShop.entity.dto.user.RegisterUserRequest;
import plantShop.repo.UserRepo;
import plantShop.service.Interface.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import plantShop.service.Interface.JwtService;
import plantShop.service.Interface.UserService;

@Service
public class AuthenticationServiceImpl implements  AuthenticationService{
//    @Autowired
    AuthenticationService authenticationService;
//    @Autowired
    UserRepo userRepository;

//    @Autowired
    UserService userService;

//    @Autowired
    JwtService jwtService;

//    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public JwtAuthenticationResponse signup(RegisterUserRequest request) {

        //validate email
        if(request.getEmail() == null || request.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        var email = userRepository.findByEmail(request.getEmail());
        if(email.isPresent()){
            throw new IllegalArgumentException("Email is already in use");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipCode(request.getZipCode());
        if(!request.getUserType()){
            user.setRole(Role.ROLE_BUYER);
        }else{
            user.setBusinessName(request.getBusinessName());
            user.setRole(Role.ROLE_SELLER);
            user.setDescription(request.getDescription());
        }
        var newUser= userService.save(user);

        String jwt = jwtService.generateToken((UserDetails) newUser);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt, user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().toString());
        return jwtAuthenticationResponse;
    }
}
