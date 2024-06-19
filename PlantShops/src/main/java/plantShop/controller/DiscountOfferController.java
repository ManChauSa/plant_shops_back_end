package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.discount.CreateOrUpdateDiscountRequest;
import plantShop.Entity.dto.discount.DiscountRespone;
import plantShop.service.Interface.DiscountOfferService;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountOfferController {

    @Autowired
    DiscountOfferService discountOfferService;

    @GetMapping("/{id}")
    public DiscountRespone getDiscountOfferById(@PathVariable("id") int id) {
        return discountOfferService.getDiscountOfferById(id);
    }

    @GetMapping()
    public List<DiscountRespone> getDiscountOffer() {
        return discountOfferService.getAllDiscountOffers();
    }

    @GetMapping("/shop")
    public List<DiscountRespone> getAllDiscountOffersOfProduct() {
//        get list discount of product when create product of seller role
        return discountOfferService.getAllDiscountOffersOfProduct();
    }


    @GetMapping("/{id}/checkout")
    public DiscountRespone getAllDiscountOffersOfShop(@PathVariable("id") int id, @RequestParam String code) {
//        get list discount of shop when checkout
        return discountOfferService.getDiscountOffersOfShop(id,code);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createDiscountOffer(@RequestBody CreateOrUpdateDiscountRequest param){
        discountOfferService.saveDiscountOffer(param);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void updateDiscountOffer(@PathVariable int id, @RequestBody CreateOrUpdateDiscountRequest param){
        discountOfferService.updateDiscountOffer(id,param);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteDiscountOffer(@PathVariable("id") int id){
        discountOfferService.deleteDiscountOffer(id);
    }
}
