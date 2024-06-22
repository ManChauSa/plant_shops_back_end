package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantShop.Entity.Address;
import plantShop.Entity.User;
import plantShop.Entity.dto.address.AddressRequest;
import plantShop.Entity.dto.address.AddressResponse;
import plantShop.repo.AddressRepo;
import plantShop.service.Interface.AddressService;

import java.time.LocalDate;

import static plantShop.common.constant.PlantShopConstants.currentUserId;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @Override
    public void addAddress(AddressRequest address) {

        User currentUser = new User();
        currentUser= userService.getCurrentUser();
        Address newAddress = new Address();

        newAddress.setAddressStreet(address.getAddressStreet());
        newAddress.setAddressLine1(address.getAddressLine1());
        newAddress.setAddressLine2(address.getAddressLine2());
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setZipCode(address.getZipCode());
        newAddress.setState(address.getState());
        newAddress.setZipCode(address.getZipCode());
        newAddress.setUser(currentUser);
        newAddress.setCreatedDate(LocalDate.now());

        addressRepo.save(newAddress);
    }

    @Override
    public void updateAddress(int userId, AddressRequest param) {
       var address = addressRepo.findAll()
               .stream().filter(a->a.getUser().getUserId() == userId).findFirst().get();
        if(address == null) {throw new IllegalArgumentException("User not found");}

        address.setAddressStreet(param.getAddressStreet());
        address.setAddressLine1(param.getAddressLine1());
        address.setAddressLine2(param.getAddressLine2());
        address.setCity(param.getCity());
        address.setCountry(param.getCountry());
        address.setZipCode(param.getZipCode());
        address.setState(param.getState());
        address.setZipCode(param.getZipCode());
        address.setCreatedDate(LocalDate.now());

        addressRepo.save(address);
    }

    @Override
    public AddressResponse getAddressByUserId() {
        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentUserId);

        var address = addressRepo.findAll()
                .stream().filter(a->a.getUser().getUserId() == currentUser.getUserId()).findFirst().orElse(null);
        return address != null ? modelMapper.map(address, AddressResponse.class) : null;
    }
}
