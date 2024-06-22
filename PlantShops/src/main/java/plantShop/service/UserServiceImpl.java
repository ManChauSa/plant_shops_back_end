package plantShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import plantShop.Entity.User;
import plantShop.Entity.dto.user.UserResponse;
import plantShop.common.constant.Role;
import plantShop.helper.ListMapper;
import plantShop.repo.UserRepo;
import plantShop.service.Interface.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    ListMapper listMapper;

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

    public List<UserResponse> getAllUnapprovedSeller() {
        List<User> user = userRepo.findAll().stream().filter(o -> Objects.equals(o.getRole(), Role.ROLE_SELLER.toString()) && (o.getIsApproved() == null || !o.getIsApproved()) ).collect(Collectors.toList());

        return listMapper.mapList(user,new UserResponse());
    }

    public void approveSeller(int id) {
        User user = userRepo.findById(id).get();
        user.setIsApproved(true);
        userRepo.save(user);
    }
}
