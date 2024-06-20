package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String listCategories,
                                                @RequestParam(required = false) String listSortTypes,
                                                @RequestParam(required = false) Integer minPrice,
                                                @RequestParam(required = false) Integer maxPrice,
                                                @RequestParam(required = false) String search) {
        if(search != null || listCategories != null || listSortTypes != null) {
            return productService.filterProduct(listCategories, listSortTypes, minPrice, maxPrice, search);
        }
        return productService.getAllProducts();
    }

    @GetMapping("/newarrival")
    public List<ProductResponse> getNewArrivalProducts() {
        return productService.findTop8ByOrderByCreatedDateDesc();
    }

    @GetMapping("/trending")
    public List<ProductResponse> getTrandingProducts() {
        return productService.findTop8ByOrderByCreatedDateDesc();
    }

    @GetMapping("/sale")
    public List<ProductResponse> getSaleProducts() {
        return productService.findTop8ByOrderByCreatedDateDesc();
    }

    @GetMapping("/seller/{id}")
    public List<ProductResponse> getAllProductsBySelelr(@PathVariable("id") int id) {

        return productService.getAllProductsBySellerId(id);
    }

    @GetMapping("/{id}")
    public ProductResponse GetProductById(@PathVariable("id") int id) {

        return productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int createProduct(@RequestBody CreateOrUpdateProductRequest param){
        return productService.addProduct(param);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public int updateProduct(@PathVariable("id") int id ,@RequestBody CreateOrUpdateProductRequest  param){
        return productService.updateProduct(id,param);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
    }


}
