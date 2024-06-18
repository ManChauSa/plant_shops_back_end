package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.category.CategoryResponse;
import plantShop.Entity.dto.category.CreateOrUpdateCategoryRequest;
import plantShop.Entity.dto.product.CreateOrUpdateProductRequest;
import plantShop.Entity.dto.product.ProductResponse;
import plantShop.service.Interface.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createProduct(@RequestBody CreateOrUpdateProductRequest param){
        productService.addProduct(param);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") int id ,@RequestBody CreateOrUpdateProductRequest  param){
        productService.updateProduct(id,param);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
    }
}
