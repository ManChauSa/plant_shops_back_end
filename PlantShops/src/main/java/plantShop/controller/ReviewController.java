package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.review.CreateReviewRequest;
import plantShop.Entity.dto.review.ReviewProductRespone;
import plantShop.service.Interface.ReviewService;


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

    @GetMapping("/{id}")
    public ReviewProductRespone getReviewsByProductId(@PathVariable("id") int productId){
        return  reviewService.getReviewByProductId(productId);
    }
}
