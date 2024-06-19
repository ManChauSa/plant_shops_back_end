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

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Boolean addAddress(AddressRequest address) {
        var currentuser= new User();
        currentuser.setUserId(3);
        Address newAddress = new Address();

        newAddress.setAddressStreet(address.getAddressStreet());
        newAddress.setAddressLine1(address.getAddressLine1());
        newAddress.setAddressLine2(address.getAddressLine2());
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setZipCode(address.getZipCode());
        newAddress.setState(address.getState());
        newAddress.setZipCode(address.getZipCode());
        newAddress.setUser(currentuser);
        newAddress.setCreatedDate(LocalDate.now());

        addressRepo.save(newAddress);
        return true;
    }

    @Override
    public Boolean updateAddress(int userId, AddressRequest param) {
       var address = addressRepo.findAll()
               .stream().filter(a->a.getUser().getUserId() == userId).findFirst().get();
        if(address != null) {throw new IllegalArgumentException("User not found");}

        var currentuser= new User();
        currentuser.setUserId(3);

        address.setAddressStreet(param.getAddressStreet());
        address.setAddressLine1(param.getAddressLine1());
        address.setAddressLine2(param.getAddressLine2());
        address.setCity(param.getCity());
        address.setCountry(param.getCountry());
        address.setZipCode(param.getZipCode());
        address.setState(param.getState());
        address.setZipCode(param.getZipCode());
        address.setUser(currentuser);
        address.setCreatedDate(LocalDate.now());

        addressRepo.save(address);
        return true;
    }

    @Override
    public AddressResponse getAddressByUserId() {
        var currentuser= new User();
        currentuser.setUserId(3);

        var address = addressRepo.findAll()
                .stream().filter(a->a.getUser().getUserId() == currentuser.getUserId()).findFirst().orElse(null);
        return address != null ? modelMapper.map(address, AddressResponse.class) : null;
    }
}
