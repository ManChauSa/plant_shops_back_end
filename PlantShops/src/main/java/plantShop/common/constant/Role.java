package plantShop.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_SELLER("ROLE_SELLER"),
    ROLE_BUYER("ROLE_BUYER");


    private String role;

    Role(String role) {
        this.role = role;
    }

}
