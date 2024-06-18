package plantShop.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantShop.common.constant.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private Boolean userType = false;

    private String businessName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String description;
    private Role role;
    private Boolean IsVerifyle = false;
}
