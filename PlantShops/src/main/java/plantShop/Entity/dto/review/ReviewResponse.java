package plantShop.Entity.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {

     Long reviewId;

     Integer productIs;
     String userId;
     String lastName;
     String firstName;
     Double rating;
     String reviewText;

     LocalDate reviewDate;
    LocalDate createdDate;
    LocalDate updateDate;
}

