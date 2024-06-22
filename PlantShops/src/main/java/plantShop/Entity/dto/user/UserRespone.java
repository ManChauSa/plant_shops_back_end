package plantShop.Entity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRespone {
     Integer userId;
     String userName;
     String email;
     String phone;
     String firstName;
     String lastName;
     String role;
}
