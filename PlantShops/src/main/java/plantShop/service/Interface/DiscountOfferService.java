package plantShop.service.Interface;

import plantShop.Entity.dto.discount.CreateOrUpdateDiscountRequest;
import plantShop.Entity.dto.discount.DiscountRespone;

import java.util.List;

public interface DiscountOfferService {

    DiscountRespone getDiscountOfferById(int id);
    List<DiscountRespone> getAllDiscountOffers();

    void saveDiscountOffer(CreateOrUpdateDiscountRequest discountOffer);
    void deleteDiscountOffer(int id);
    void updateDiscountOffer(int id,CreateOrUpdateDiscountRequest discountOffer);
}
