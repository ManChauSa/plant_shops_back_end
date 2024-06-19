package plantShop.Entity.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    String addressStreet;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String zipCode;
    String country;
}
