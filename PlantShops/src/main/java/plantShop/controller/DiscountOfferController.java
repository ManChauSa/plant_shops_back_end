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
    public DiscountRespone getDiscountOfferById(@PathVariable int id) {
        return discountOfferService.getDiscountOfferById(id);
    }

    @GetMapping()
    public List<DiscountRespone> getDiscountOffer() {
        return discountOfferService.getAllDiscountOffers();
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
