package plantShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plantShop.Entity.dto.product.CreateOrUpdateProductRequest;
import plantShop.Entity.dto.product.ProductPaging;
import plantShop.Entity.dto.product.ProductResponse;
import plantShop.service.Interface.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{pageSize}/{page}")
    public ProductPaging getAllProducts(@RequestParam(required = false) String listCategories,
                                        @RequestParam(required = false) String listSortTypes,
                                        @RequestParam(required = false) Integer minPrice,
                                        @RequestParam(required = false) Integer maxPrice,
                                        @RequestParam(required = false) String search,
                                        @PathVariable("pageSize") int pageSize,
                                        @PathVariable("page") int page) {
        return productService.filterProduct(listCategories, listSortTypes, minPrice, maxPrice, search, pageSize, page);
    }

    @GetMapping("/newarrival")
    public List<ProductResponse> getNewArrivalProducts() {
        return productService.findTop8ByOrderByCreatedDateDesc();
    }

    @GetMapping("/trending")
    public List<ProductResponse> getTrandingProducts() {
        return productService.findTop8ByOrderByQuantityDesc();
    }

    @GetMapping("/sale")
    public List<ProductResponse> getSaleProducts() {
        return productService.findTop8ByOrderByDiscounts();
    }

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @GetMapping("/seller/products")
    public List<ProductResponse> getAllProductsBySeller() {

        return productService.getAllProductsBySellerId();
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
