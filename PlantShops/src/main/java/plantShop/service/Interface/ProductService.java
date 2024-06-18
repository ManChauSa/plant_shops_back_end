package plantShop.service.Interface;

import plantShop.Entity.dto.product.CreateOrUpdateProductRequest;
import plantShop.Entity.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {
//    seller crud
//    b.	Product (CRUD). If a product has already been purchased, it cannot be deleted.
    ProductResponse getProductById(int id);
    List<ProductResponse> getAllProducts();
    void addProduct(CreateOrUpdateProductRequest product);
    void updateProduct(int productId, CreateOrUpdateProductRequest product);
    void deleteProduct(int productId);

}
