package plantShop.service.Interface;

import plantShop.Entity.dto.review.CreateReviewRequest;
import plantShop.Entity.dto.review.ReviewProductRespone;


public interface ReviewService {
    void addReview(CreateReviewRequest review);
    ReviewProductRespone getReviewByProductId(int id);
    void deleteReview(int id);
}
