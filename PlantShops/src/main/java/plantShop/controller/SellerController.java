package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.user.UserResponse;
import plantShop.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> getAllUnapprovedSeller() {
        return userService.getAllUnapprovedSeller();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("approve/{id}")
    public void approveSeller(@PathVariable("id") int id){
        userService.approveSeller(id);
    }
}
