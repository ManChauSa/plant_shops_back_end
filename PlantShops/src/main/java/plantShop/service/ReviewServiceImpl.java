package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plantShop.Entity.Product;
import plantShop.Entity.Review;
import plantShop.Entity.User;
import plantShop.Entity.dto.order.OrderItems;
import plantShop.Entity.dto.review.CreateReviewRequest;
import plantShop.Entity.dto.review.ReviewProductRespone;
import plantShop.Entity.dto.review.ReviewResponse;
import plantShop.helper.ListMapper;
import plantShop.repo.ReviewRepo;
import plantShop.service.Interface.ProductService;
import plantShop.service.Interface.ReviewService;

import java.time.LocalDate;

import static plantShop.common.constant.PlantShopConstants.currentUserId;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Autowired
    ProductService productService;

    @Override
    public void addReview(CreateReviewRequest request) {
        Review newReview = new Review();

        var product = productService.getProductById(request.getProductId());
        if(product == null) {throw new IllegalArgumentException("Product not found");}

        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentUserId);

        newReview.setUser(currentUser);
        newReview.setProduct(modelMapper.map(product, Product.class));
        newReview.setRating(request.getRating());
        newReview.setReviewText(request.getReviewText());
        newReview.setReviewDate(LocalDate.now());
        newReview.setCreatedDate(LocalDate.now());
        newReview.setUpdateDate(LocalDate.now());

        reviewRepo.save(newReview);
    }

    @Override
    public ReviewProductRespone getReviewByProductId(int id) {
        var reviews = reviewRepo.findAll().stream().filter(r->r.getProduct().getProductId() == id).toList();
        if(reviews.isEmpty()) return new ReviewProductRespone();

        var rating = reviews.stream().mapToDouble(Review::getRating).average().getAsDouble();
        var result= new ReviewProductRespone();
        result.setProductId(id);
        result.setReviews(listMapper.mapList(reviews,new ReviewResponse()));
        result.setRatingAvg(rating);
        return result;
    }

    @Override
    public void deleteReview(int id) {
        var review = reviewRepo.findById(id).orElse(null);
        if(review == null) {throw new IllegalArgumentException("Review not found");}

        reviewRepo.delete(review);
    }
}
