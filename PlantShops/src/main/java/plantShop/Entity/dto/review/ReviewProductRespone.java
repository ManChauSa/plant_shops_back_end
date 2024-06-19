package plantShop.Entity.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewProductRespone {
    Integer productId;
    List<ReviewResponse> reviews;
    Double ratingAvg;

}
