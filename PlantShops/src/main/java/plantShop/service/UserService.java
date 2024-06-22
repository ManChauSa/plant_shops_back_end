package plantShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import plantShop.Entity.User;
import plantShop.repo.UserRepo;

import java.time.LocalDate;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                var user = userRepo.findUserByUserName(username);
                if (user == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                return user;
            }
        };
    }

    public User save(User newUser) {
        if (newUser.getEmail() == null) {
            newUser.setCreatedDate(LocalDate.now());
        }

        newUser.setUpdateDate(LocalDate.now());
        return userRepo.save(newUser);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

    //use for seed data
    public void add(User user){
        userRepo.save(user);
    }
}
