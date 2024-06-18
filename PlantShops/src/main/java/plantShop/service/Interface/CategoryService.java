package plantShop.service.Interface;

import plantShop.Entity.Category;
import plantShop.Entity.dto.category.CategoryResponse;
import plantShop.Entity.dto.category.CreateOrUpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    void addCategory(CreateOrUpdateCategoryRequest category);
    void deleteCategory(int categoryId);
    void updateCategory(int categoryId, CreateOrUpdateCategoryRequest param);

    CategoryResponse getCategoryById(int id);
    List<CategoryResponse> getAllCategories();
}
