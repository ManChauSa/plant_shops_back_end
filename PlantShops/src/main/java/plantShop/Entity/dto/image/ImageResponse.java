package plantShop.Entity.dto.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
     Integer imageId;
     String imageUrl;
     LocalDate createDate;
     LocalDate updateDate;
}
