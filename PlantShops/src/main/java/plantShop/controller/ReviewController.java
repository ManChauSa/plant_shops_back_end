package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.review.CreateReviewRequest;
import plantShop.Entity.dto.review.ReviewProductRespone;
import plantShop.Entity.dto.review.ReviewResponse;
import plantShop.service.Interface.ReviewService;

import java.util.List;


@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createReviewProduct(@RequestBody CreateReviewRequest request) {
        reviewService.addReview(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable("id") int id){
         reviewService.deleteReview(id);
    }

    @GetMapping("/personal/{id}")
    public ReviewResponse getBuyerReviewsByProductId(@PathVariable("id") int productId){
        return  reviewService.getBuyerReviewsByProductId(productId);
    }

    @GetMapping("/{id}")
    public List<ReviewResponse> getReviewsByProductId(@PathVariable("id") int productId){
        return  reviewService.getReviewByProductId(productId);
    }

}
