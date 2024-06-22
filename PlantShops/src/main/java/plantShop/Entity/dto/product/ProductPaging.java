package plantShop.Entity.dto.product;

import java.util.List;

public class ProductPaging {
    Integer totalPages;

    List<ProductResponse> products;

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
