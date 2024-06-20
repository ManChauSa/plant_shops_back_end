package plantShop.Entity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.common.constant.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
     Integer userId;
     String userName;
     String email;
     String phone;
     String name;
     String role;
}
