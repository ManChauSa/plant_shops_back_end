package plantShop.service.Interface;

import plantShop.Entity.dto.product.CreateOrUpdateProductRequest;
import plantShop.Entity.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getProductById(int id);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getAllProductsBySellerId();
    int addProduct(CreateOrUpdateProductRequest product);
    int updateProduct(int productId, CreateOrUpdateProductRequest product);
    void deleteProduct(int productId);

    List<ProductResponse> filterProduct(String categoryIds, String listSortTypes, int minPrice, int maxPrice, String search);
    List<ProductResponse> findTop8ByOrderByCreatedDateDesc();

}
