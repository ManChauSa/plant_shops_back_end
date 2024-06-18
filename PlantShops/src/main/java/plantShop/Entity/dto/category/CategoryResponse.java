package plantShop.Entity.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantShop.Entity.Category;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
     Integer categoryId;

     String categoryName;
     String categoryDesc;
     LocalDate createdDate;
     LocalDate updateDate;
     CategoryResponse parentCategory;
}
