package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plantShop.Entity.Product;
import plantShop.Entity.Review;
import plantShop.Entity.User;
import plantShop.Entity.dto.review.CreateReviewRequest;
import plantShop.Entity.dto.review.ReviewResponse;
import plantShop.helper.ListMapper;
import plantShop.repo.ReviewRepo;
import plantShop.service.Interface.ProductService;
import plantShop.service.Interface.ReviewService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private UserServiceImpl userService;

    @Override
    public void addReview(CreateReviewRequest request) {
        Review newReview = new Review();

        var product = productService.getProductById(request.getProductId());
        if(product == null) {throw new IllegalArgumentException("Product not found");}

        // Todo get current user login
        User currentUser = userService.getCurrentUser();

        newReview.setUser(currentUser);
        newReview.setProduct(modelMapper.map(product, Product.class));
        newReview.setReviewText(request.getReviewText());
        newReview.setReviewDate(LocalDate.now());
        newReview.setCreatedDate(LocalDate.now());
        newReview.setUpdateDate(LocalDate.now());
        newReview.setName(currentUser.getName());
        reviewRepo.save(newReview);
    }

    @Override
    public List<ReviewResponse> getReviewByProductId(int id) {
        var reviews = reviewRepo.findAll().stream().filter(r->r.getProduct().getProductId() == id).toList();
        if(reviews.isEmpty())
            return new ArrayList<>();

        return listMapper.mapList(reviews,new ReviewResponse());
    }

    @Override
    public ReviewResponse getBuyerReviewsByProductId(int id) {
        User currentUser = userService.getCurrentUser();

        var reviews = reviewRepo.findAll().stream().filter(r->r.getProduct().getProductId() == id).toList();
        var review = reviews.stream().filter(r->r.getProduct().getProductId() == id && r.getUser().getUsername() == currentUser.getUsername()).findFirst();
        if(review.isEmpty())
            return null;
        ReviewResponse response = new ReviewResponse();
        response.setName(review.get().getUser().getUsername());
        response.setReviewText(review.get().getReviewText());
        return response;
    }

    @Override
    public void deleteReview(int id) {
        var review = reviewRepo.findById(id).orElse(null);
        if(review == null) {throw new IllegalArgumentException("Review not found");}

        reviewRepo.delete(review);
    }
}
