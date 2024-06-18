package plantShop.service.Interface;

import plantShop.Entity.dto.product.CreateOrUpdateProductRequest;
import plantShop.Entity.dto.product.ProductResponse;
import plantShop.common.constant.SortType;

import java.util.List;

public interface ProductService {

    ProductResponse getProductById(int id);
    List<ProductResponse> getAllProducts();
    void addProduct(CreateOrUpdateProductRequest product);
    void updateProduct(int productId, CreateOrUpdateProductRequest product);
    void deleteProduct(int productId);

    List<ProductResponse> filterProduct(String categoryIds, String listSortTypes);
}
