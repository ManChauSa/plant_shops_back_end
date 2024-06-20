package plantShop.Entity.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
     Integer userId;

     String userName;
     String password;
     String phone;
     String firstName;
     String lastName;
     String role;
     LocalDate createdDate;
     LocalDate updateDate;
}
