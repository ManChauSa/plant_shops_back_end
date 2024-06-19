package plantShop.service.Interface;

import plantShop.Entity.dto.address.AddressRequest;
import plantShop.Entity.dto.address.AddressResponse;

public interface AddressService {
    Boolean addAddress(AddressRequest address);
    Boolean updateAddress(int userId,AddressRequest address);
    AddressResponse getAddressByUserId();
}
