package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantShop.Entity.DiscountAndOffer;
import plantShop.Entity.dto.discount.CreateOrUpdateDiscountRequest;
import plantShop.Entity.dto.discount.DiscountRespone;
import plantShop.helper.ListMapper;
import plantShop.repo.DiscountOfferRepo;
import plantShop.service.Interface.DiscountOfferService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class DiscountOfferServiceImpl implements DiscountOfferService {

    @Autowired
    DiscountOfferRepo discountOfferRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;

    @Override
    public DiscountRespone getDiscountOfferById(int id) {
        return modelMapper.map(discountOfferRepo.findById(id), DiscountRespone.class);
    }

    @Override
    public List<DiscountRespone> getAllDiscountOffers() {
        List<DiscountAndOffer> discountOffers = discountOfferRepo.findAll();
        discountOffers.sort(Comparator.comparing(DiscountAndOffer::getCreatedDate).reversed());
        return listMapper.mapList(discountOffers, new DiscountRespone());
    }

    @Override
    public void saveDiscountOffer(CreateOrUpdateDiscountRequest discountOffer) {
        if(discountOffer == null) { throw  new  IllegalArgumentException("Parameter discountOffer cannot be null"); }
        DiscountAndOffer newDiscount = new DiscountAndOffer();
        newDiscount.setDiscountName(discountOffer.getDiscountName());
        newDiscount.setDescription(discountOffer.getDescription());
        newDiscount.setStartDate(discountOffer.getStartDate());
        newDiscount.setEndDate(discountOffer.getEndDate());
        newDiscount.setPercent(discountOffer.getPercent());
        newDiscount.setCode(discountOffer.getDiscountName().toUpperCase());
        newDiscount.setCreatedDate(LocalDate.now());
        newDiscount.setUpdateDate(LocalDate.now());

        discountOfferRepo.save(newDiscount);
    }

    @Override
    public void deleteDiscountOffer(int id) {
        var discountOffer = discountOfferRepo.findById(id);
        if(discountOffer != null) {throw new IllegalArgumentException("Discount offer not found");}

        discountOfferRepo.deleteById(id);
    }

    @Override
    public void updateDiscountOffer(int id, CreateOrUpdateDiscountRequest param) {
        var discountOffer = discountOfferRepo.findById(id).get();
        if(discountOffer == null) {throw new IllegalArgumentException("Discount offer not found");}

        discountOffer.setDiscountName(param.getDiscountName());
        discountOffer.setDescription(param.getDescription());
        discountOffer.setStartDate(param.getStartDate());
        discountOffer.setEndDate(param.getEndDate());
        discountOffer.setPercent(param.getPercent());
        discountOffer.setCode(param.getDiscountName().toUpperCase());
        discountOffer.setUpdateDate(LocalDate.now());

        discountOfferRepo.save(discountOffer);

    }
}
