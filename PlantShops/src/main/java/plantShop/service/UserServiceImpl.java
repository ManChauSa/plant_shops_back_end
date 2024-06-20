package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import plantShop.Entity.dto.account.User;
import plantShop.repo.UserRepo;
import plantShop.service.Interface.UserService;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return (UserDetails) userRepo.findAll().stream().filter(u->u.getUserName().equals(username))
                        .findFirst().orElseThrow(() -> new IllegalArgumentException("User not found"));
            }
        };
    }

    @Override
    public User save(User newUser) {

         if (newUser.getUserName() == null) {
           throw new IllegalArgumentException("Username is required");
         }
        var checkExist = userRepo.findAll().stream().filter(u->u.getUserName().equals(newUser.getUserName()))
                .findFirst().orElse(null);
         if (checkExist != null) { throw new IllegalArgumentException("Username already exists"); }

        plantShop.Entity.User userEntity = new plantShop.Entity.User();
         userEntity.setUserName(newUser.getUserName());
         userEntity.setPassword(newUser.getPassword());
         userEntity.setFirstName(newUser.getFirstName());
         userEntity.setLastName(newUser.getLastName());

         var user = userRepo.save(userEntity);
         return modelMapper.map(user, User.class);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }
}
