package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantShop.Entity.Category;
import plantShop.Entity.dto.category.CategoryResponse;
import plantShop.helper.ListMapper;
import plantShop.repo.CategoryRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import plantShop.Entity.dto.category.CreateOrUpdateCategoryRequest;
import plantShop.service.Interface.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
//    @Autowired
    CategoryRepo categoryRepo;
    CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Override
    public void addCategory(CreateOrUpdateCategoryRequest category) {
        Category newCategory = new Category();
        if(category.getCategoryParentID() != null){
            var categoryParent = getCategoryById(category.getCategoryParentID());
            if(categoryParent == null){
                throw new IllegalArgumentException("Category parent not exist");
            }
            newCategory.setParentCategory(modelMapper.map(categoryParent.getParentCategory(),Category.class));
        }
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setCategoryDesc(category.getCategoryDesc());
        newCategory.setCreatedDate(LocalDate.now());
        categoryRepo.save(newCategory);
    }

    @Override
    public void deleteCategory(int categoryId) {
        if(categoryId == 0 ) throw new IllegalArgumentException("Category id is null");
        var category = categoryRepo.findById(categoryId);
        if(category == null) throw new IllegalArgumentException("Category not exist");
        categoryRepo.deleteById(categoryId);
    }

    @Override
    public void updateCategory(int categoryId, CreateOrUpdateCategoryRequest param) {
//        var updatedCategory = new Category();
        if(categoryId == 0 ) throw new IllegalArgumentException("Category id is null");
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        if(categoryOptional.isEmpty()) throw new IllegalArgumentException("Category not exist");
        Category category = categoryOptional.get();
        if(param.getCategoryParentID() != null){
            var categoryParent = getCategoryById(param.getCategoryParentID());
            if(categoryParent == null){
                throw new IllegalArgumentException("Category parent not exist");
            }
            category.setParentCategory(modelMapper.map(categoryParent.getParentCategory(),Category.class));
        }
        category.setCategoryName(param.getCategoryName());
        category.setCategoryDesc(param.getCategoryDesc());
        category.setUpdateDate(LocalDate.now());
        categoryRepo.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(int id) {
        return modelMapper.map(categoryRepo.findById(id), CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return (List<CategoryResponse>) listMapper.mapList(categoryRepo.findAll(), new CategoryResponse());
    }
}
