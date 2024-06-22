package plantShop.service.Interface;

import plantShop.Entity.dto.review.CreateReviewRequest;
import plantShop.Entity.dto.review.ReviewProductRespone;
import plantShop.Entity.dto.review.ReviewResponse;

import java.util.List;


public interface ReviewService {
    void addReview(CreateReviewRequest review);
    List<ReviewResponse> getReviewByProductId(int id);
    ReviewResponse getBuyerReviewsByProductId(int id);
    void deleteReview(int id);
}
