package plantShop.service.Interface;

import plantShop.Entity.dto.address.AddressRequest;
import plantShop.Entity.dto.address.AddressResponse;

public interface AddressService {
    void addAddress(AddressRequest address);
    void updateAddress(int userId,AddressRequest address);
    AddressResponse getAddressByUserId();
}
