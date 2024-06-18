package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.category.CategoryResponse;
import plantShop.Entity.dto.category.CreateOrUpdateCategoryRequest;
import plantShop.service.Interface.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createCategory(@RequestBody CreateOrUpdateCategoryRequest  param){
        categoryService.addCategory(param);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateCategory(@PathVariable("id") int id ,@RequestBody CreateOrUpdateCategoryRequest  param){
        categoryService.updateCategory(id,param);
    }

}
