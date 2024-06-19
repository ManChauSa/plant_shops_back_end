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
import plantShop.repo.DiscountOfferRepo;
import plantShop.repo.ProductRepo;
import plantShop.repo.ReviewRepo;
import plantShop.service.Interface.CategoryService;
import plantShop.service.Interface.DiscountOfferService;
import plantShop.service.Interface.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static plantShop.common.constant.PlantShopConstants.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    ReviewRepo reviewRepo;

    DiscountOfferService discountOfferService;
    CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;
    @Autowired
    private DiscountOfferRepo discountOfferRepo;


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
        var category = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), Category.class);
        if (category == null) throw new IllegalArgumentException("Category not found");

        /// Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentSellerId);

        // get discount
        var discounts = getDiscountOffers(product.getDiscountIds());

        newProduct.setCategory(category);
        newProduct.setPrice(product.getPrice());
        newProduct.setProductName(product.getProductName());
        newProduct.setDescription(product.getDescription());
        newProduct.setProductType(product.getProductType());
        newProduct.setPrice(product.getPrice());
        newProduct.setImageUrl(product.getImageUrl());
        newProduct.setInventoryCount(product.getInventoryCount());
        newProduct.setStatus(product.getStatus());
        newProduct.setSeller(currentUser);
        newProduct.setDiscounts(discounts);
        product.setCreatedDate(LocalDate.now());

        productRepo.save(newProduct);
    }

    @Override
    public void updateProduct(int productId, CreateOrUpdateProductRequest param) {
        var product = productRepo.findById(productId).get();
        if (product == null) throw new IllegalArgumentException("Product not found");
        var category = modelMapper.map(categoryService.getCategoryById(param.getCategoryId()), Category.class);
        if (category == null) throw new IllegalArgumentException("Category not found");

        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentSellerId);

        var discounts = getDiscountOffers(param.getDiscountIds());

        product.setCategory(category);
        product.setPrice(product.getPrice());
        product.setProductName(product.getProductName());
        product.setDescription(product.getDescription());
        product.setProductType(product.getProductType());
        product.setPrice(product.getPrice());
        product.setImageUrl(product.getImageUrl());
        product.setInventoryCount(product.getInventoryCount());
        product.setStatus(product.getStatus());
        product.setSeller(currentUser);
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

    private List<DiscountAndOffer> getDiscountOffers(List<Integer> discountOfferIds) {

        var discounts = discountOfferRepo.findAll()
                .stream()
                .filter(dc-> dc.getDiscountType() == discountOfProduct && discountOfferIds.contains(dc.getDiscountOfferId()))
                .collect(Collectors.toList());
        if (discounts.isEmpty()) {return new ArrayList<DiscountAndOffer>();}
        return  listMapper.mapList(discounts, new DiscountAndOffer());
    }
}


