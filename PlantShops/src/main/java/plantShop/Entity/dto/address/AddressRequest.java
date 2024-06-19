package plantShop.Entity.dto.address;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
     String addressStreet;
     String addressLine1;
     String addressLine2;
     String city;
     String state;
     String zipCode;
     String country;
}
