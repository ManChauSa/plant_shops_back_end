package plantShop.entity.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    String confirmPassword;
    Boolean userType;
    String businessName;
    String phoneNumber;
    String address;
    String city;
    String state;
    String zipCode;
    String description;
}
