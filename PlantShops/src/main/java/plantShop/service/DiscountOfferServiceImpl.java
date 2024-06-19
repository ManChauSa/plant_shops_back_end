package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantShop.Entity.DiscountAndOffer;
import plantShop.Entity.User;
import plantShop.Entity.dto.discount.CreateOrUpdateDiscountRequest;
import plantShop.Entity.dto.discount.DiscountRespone;
import plantShop.helper.ListMapper;
import plantShop.repo.DiscountOfferRepo;
import plantShop.service.Interface.DiscountOfferService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static plantShop.common.constant.PlantShopConstants.*;

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
    public List<DiscountRespone> getAllDiscountOffersOfProduct() {
        // get list discount of product when create product seller
        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentSellerId);
        var discounts = discountOfferRepo.findAll().stream()
                        .filter(d-> Objects.equals(d.getDiscountType(), discountOfProduct) && (d.getSeller() != null && d.getSeller().getUserId() == currentUser.getUserId()))
                        .collect(Collectors.toList());
        if (discounts.isEmpty()) {return new ArrayList<DiscountRespone>();}
        return listMapper.mapList(discounts, new DiscountRespone());
    }

    @Override
    public DiscountRespone getDiscountOffersOfShop(int shopId, String code) {
        // get when checkout
        var discounts = discountOfferRepo.findAll().stream()
                .filter(d-> d.getDiscountType() == discountOfShop && (d.getSeller() != null && d.getSeller().getUserId() == shopId) && d.getCode().equals(code)).findFirst();
        if (discounts.isEmpty()) {
            return new DiscountRespone();
        }
        return modelMapper.map(discounts, DiscountRespone.class);
    }

    @Override
    public List<DiscountRespone> getAllDiscountOffers() {
        // seller get all discount
        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentSellerId);
        var a= discountOfferRepo.findAll();
        List<DiscountAndOffer> discountOffers = discountOfferRepo.findAll().stream()
                .filter(d -> d.getSeller() != null && d.getSeller().getUserId() != null && d.getSeller().getUserId().equals(currentUser.getUserId()))
                .sorted(Comparator.comparing(DiscountAndOffer::getCreatedDate).reversed())
                .collect(Collectors.toList());
        discountOffers.sort(Comparator.comparing(DiscountAndOffer::getCreatedDate).reversed());
        return listMapper.mapList(discountOffers, new DiscountRespone());
    }

    @Override
    public void saveDiscountOffer(CreateOrUpdateDiscountRequest discountOffer) {
        if(discountOffer == null) { throw  new  IllegalArgumentException("Parameter discountOffer cannot be null"); }
        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentSellerId);

        var discountNameUpper = discountOffer.getDiscountName().toUpperCase().replace(" ","");
        var code = discountNameUpper+discountOffer.getPercent();
        DiscountAndOffer newDiscount = new DiscountAndOffer();
        newDiscount.setDiscountName(discountOffer.getDiscountName());
        newDiscount.setDescription(discountOffer.getDescription());
        newDiscount.setStartDate(discountOffer.getStartDate());
        newDiscount.setEndDate(discountOffer.getEndDate());
        newDiscount.setPercent(discountOffer.getPercent());
        newDiscount.setCode(code);
        newDiscount.setDiscountType(discountOffer.getDiscountType());
        newDiscount.setSeller(currentUser);
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
        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentSellerId);

        discountOffer.setDiscountName(param.getDiscountName());
        discountOffer.setDescription(param.getDescription());
        discountOffer.setStartDate(param.getStartDate());
        discountOffer.setEndDate(param.getEndDate());
        discountOffer.setPercent(param.getPercent());
        discountOffer.setCode(param.getDiscountName().toUpperCase());
        discountOffer.setDiscountType(param.getDiscountType());
        discountOffer.setSeller(currentUser);
        discountOffer.setUpdateDate(LocalDate.now());

        discountOfferRepo.save(discountOffer);

    }
}
