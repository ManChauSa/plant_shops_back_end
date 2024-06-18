package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantShop.Entity.*;
import plantShop.Entity.dto.product.CreateOrUpdateProductRequest;
import plantShop.Entity.dto.product.ProductResponse;
import plantShop.common.constant.SortType;
import plantShop.helper.ListMapper;
import plantShop.repo.CategoryRepo;
import plantShop.repo.ProductRepo;
import plantShop.repo.ReviewRepo;
import plantShop.service.Interface.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    ReviewRepo reviewRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;


    @Override
    public ProductResponse getProductById(int id) {
        return modelMapper.map(productRepo.findById(id), ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return (List<ProductResponse>) listMapper.mapList(productRepo.findAll(), new ProductResponse());
    }

    @Override
    public void addProduct(CreateOrUpdateProductRequest product) {
        Product newProduct = new Product();
        var category = categoryRepo.findById(product.getCategoryId());
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }

        // get seller current login
        //get current user login
//        fake data
        var currentUserId = 2;
        var user = new User();
        user.setUserId(currentUserId);
        var seller = new Seller();
        seller.setSellerName("Garden Paradise");
        seller.setSellerId(1);
        seller.setUser(user);
        // get discount
        var discount = new ArrayList<DiscountAndOffer>();

        newProduct.setCategory(category.get());
        newProduct.setPrice(product.getPrice());
        newProduct.setProductName(product.getProductName());
        newProduct.setDescription(product.getDescription());
        newProduct.setProductType(product.getProductType());
        newProduct.setPrice(product.getPrice());
        newProduct.setImageUrl(product.getImageUrl());
        newProduct.setInventoryCount(product.getInventoryCount());
        newProduct.setStatus(product.getStatus());
        newProduct.setSeller(user);
        newProduct.setDiscounts(discount);
        product.setCreatedDate(LocalDate.now());

        productRepo.save(newProduct);
    }

    @Override
    public void updateProduct(int productId, CreateOrUpdateProductRequest param) {
        var product = productRepo.findById(productId).get();
        if (product == null) throw new IllegalArgumentException("Product not found");
        var category = categoryRepo.findById(param.getCategoryId());
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
        // fake data
        var currentUserId = 2;
        var user = new User();
        user.setUserId(currentUserId);
        // find discount by discount id
        var discounts = new ArrayList<DiscountAndOffer>();
        product.setCategory(category.get());
        product.setPrice(product.getPrice());
        product.setProductName(product.getProductName());
        product.setDescription(product.getDescription());
        product.setProductType(product.getProductType());
        product.setPrice(product.getPrice());
        product.setImageUrl(product.getImageUrl());
        product.setInventoryCount(product.getInventoryCount());
        product.setStatus(product.getStatus());
        product.setSeller(user);
        product.setDiscounts(discounts);
        product.setUpdateDate(LocalDate.now());

        productRepo.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        var product = productRepo.findById(productId).get();
        if (product == null) throw new IllegalArgumentException("Product not found");
        productRepo.deleteById(productId);
    }

    @Override
    public List<ProductResponse> filterProduct(String categoryIdsStr, String listSortTypesStr) {
        List<Product> products;
        List<Integer> categoryIds = Arrays.stream(categoryIdsStr.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<SortType> listSortTypes = Arrays.stream(listSortTypesStr.split(","))
                    .map(Integer::parseInt)
                    .map(index -> SortType.values()[index])
                    .collect(Collectors.toList());
        //get products
        products = productRepo.findAll();

        // Sort by category
        if (categoryIds != null) {
            products = products.stream()
                    .filter(p -> categoryIds.contains(p.getCategory().getCategoryId()))
                    .collect(Collectors.toList());
        }
    // sort by sort type
        if (listSortTypes != null) {
            for (SortType sortType : listSortTypes) {
                switch (sortType) {
                    case NEW_ARRIVALS:
                        products.sort(Comparator.comparing(Product::getCreatedDate).reversed());
                    case NAME_ASC:
                        products.sort(Comparator.comparing(Product::getProductName));
                        break;
                    case NAME_DESC:
                        products.sort(Comparator.comparing(Product::getProductName).reversed());
                        break;
                    case PRICE_ASC:
                        products.sort(Comparator.comparing(Product::getPrice));
                        break;
                    case PRICE_DESC:
                        products.sort(Comparator.comparing(Product::getPrice).reversed());
                        break;
                    case RATING:
                        products.sort(Comparator.comparing(p -> getAverageRating(p.getProductId())));
                    default:
                        break;
                }
            }
        }

        return listMapper.mapList(products, new ProductResponse());

    }

    private Double getAverageRating(int id) {
        return reviewRepo.findById(id).stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0); // return 0.0 if there are no reviews
    }
}


