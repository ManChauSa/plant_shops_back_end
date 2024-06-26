package plantShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantShop.Entity.*;
import plantShop.Entity.dto.image.ImageResponse;
import plantShop.Entity.dto.product.CreateOrUpdateProductRequest;
import plantShop.Entity.dto.product.ProductPaging;
import plantShop.Entity.dto.product.ProductResponse;
import plantShop.common.constant.SortType;
import plantShop.helper.ListMapper;
import plantShop.repo.*;
import plantShop.service.Interface.CategoryService;
import plantShop.service.Interface.DiscountOfferService;
import plantShop.service.Interface.ProductService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static plantShop.common.constant.PlantShopConstants.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    ImageRepo imageRepo;

    @Autowired
    DiscountOfferService discountOfferService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private DiscountOfferRepo discountOfferRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;


    @Override
    public ProductResponse getProductById(int id) {
        ProductResponse result;
        result = modelMapper.map(productRepo.findById(id), ProductResponse.class);
        result.setImages(getImagesByProductId(result.getProductId()));

        return result;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> result;
        result= listMapper.mapList(productRepo.findAll(), new ProductResponse());
        result.forEach(r->{
            r.setImages(getImagesByProductId(r.getProductId()));
        });
        return result;
    }

    @Override
    public List<ProductResponse> getAllProductsBySellerId() {

        User currentUser = userService.getCurrentUser();
        if(currentUser == null)
            return new ArrayList<>();

        List<ProductResponse> result;
        result= listMapper.mapList(productRepo.findAll().stream().filter(p->p.getSeller() != null && p.getSeller().getUserId().equals(currentUser.getUserId())).toList(), new ProductResponse());
        result.forEach(r->{
            r.setImages(getImagesByProductId(r.getProductId()));
        });
        return result;
    }

    @Override
    public int addProduct(CreateOrUpdateProductRequest product) {

        User currentUser = userService.getCurrentUser();

        if(!currentUser.getIsApproved())
            throw new IllegalArgumentException("Seller does not approved by Admin.");

        Product newProduct = new Product();
        var category = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), Category.class);
        if (category == null) throw new IllegalArgumentException("Category not found");

        // get discount
        List<DiscountAndOffer> discounts;
        if(product.getDiscountIds() != null) {
            discounts = getDiscountOffers(product.getDiscountIds());
            newProduct.setDiscounts(discounts);
        }

        newProduct.setCategory(category);
        newProduct.setPrice(product.getPrice());
        newProduct.setProductName(product.getProductName());
        newProduct.setDescription(product.getDescription());
        newProduct.setProductType(product.getProductType());
        newProduct.setPrice(product.getPrice());
        newProduct.setInventoryCount(product.getInventoryCount());
        newProduct.setStatus(product.getStatus());
        newProduct.setSeller(currentUser);
        product.setCreatedDate(LocalDate.now());

        var nProduct = productRepo.save(newProduct);

        if(product.getImages() != null)
        {
            List<Image> images = new ArrayList<>();
            product.getImages().forEach(p->{
                        Image image = new Image();
                        image.setCreateDate(LocalDate.now());
                        image.setUpdateDate(LocalDate.now());
                        image.setImageUrl(p);
                        image.setProduct(nProduct);
                        images.add(image);
                    }
            );
            imageRepo.saveAll(images);
        }
        return nProduct.getProductId();
    }

    @Override
    public int updateProduct(int productId, CreateOrUpdateProductRequest param) {
        var product = productRepo.findById(productId).get();
        if (product == null) throw new IllegalArgumentException("Product not found");
        var category = modelMapper.map(categoryService.getCategoryById(param.getCategoryId()), Category.class);
        if (category == null) throw new IllegalArgumentException("Category not found");

        // Todo get current user login
        User currentUser = new User();
        currentUser.setUserId(currentSellerId);
        List<DiscountAndOffer> discounts;
        if(param.getDiscountIds() != null) {
            discounts = getDiscountOffers(param.getDiscountIds());
            product.setDiscounts(discounts);
        }


        product.setCategory(category);
        product.setPrice(param.getPrice());
        product.setProductName(param.getProductName());
        product.setDescription(param.getDescription());
        product.setProductType(param.getProductType());
        product.setPrice(param.getPrice());
        product.setInventoryCount(param.getInventoryCount());
        product.setStatus(param.getStatus());
        //product.setSeller(currentUser);
        product.setUpdateDate(LocalDate.now());

        var newProduct = productRepo.save(product);

        List<Image> images = new ArrayList<>();
        param.getImages().forEach(p->{
                    Image image = new Image();
                    image.setCreateDate(LocalDate.now());
                    image.setUpdateDate(LocalDate.now());
                    image.setImageUrl(p);
                    image.setProduct(newProduct);
                    images.add(image);
                }
        );
        imageRepo.saveAll(images);

        return newProduct.getProductId();
    }

    @Override
    public void deleteProduct(int productId) {
        var product = productRepo.findById(productId).get();
        if (product == null) throw new IllegalArgumentException("Product not found");
        if (orderItemRepo.countOrderItemByProductId(productId) > 0)
            throw new IllegalArgumentException("Product has been sold.");
        productRepo.deleteById(productId);
    }

    @Override
    public ProductPaging filterProduct(String categoryIdsStr, String listSortTypesStr, int minPrice, int maxPrice, String search, int pageSize, int page) {
        List<ProductResponse> products;

        //get products
        products = listMapper.mapList(productRepo.findAll(), new ProductResponse());;

        // filter by search key
        if (search != null) {
            String searchKey = search.toLowerCase();
            products = products.stream()
                    .filter(p -> p.getProductName().toLowerCase().contains(searchKey) || p.getDescription().toLowerCase().contains(searchKey))
                    .collect(Collectors.toList());
        }

        products = products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        products.forEach(p->{
            p.setImages(getImagesByProductId(p.getProductId()));
        });

        // Sort by category
        if (categoryIdsStr != null) {

            List<Integer> categoryIds = Arrays.stream(categoryIdsStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            products = products.stream()
                    .filter(p -> categoryIds.contains(p.getCategory().getCategoryId()))
                    .collect(Collectors.toList());
        }
        // sort by sort type
        if (listSortTypesStr != null) {

            List<SortType> listSortTypes = Arrays.stream(listSortTypesStr.split(","))
                    .map(Integer::parseInt)
                    .map(index -> SortType.values()[index])
                    .collect(Collectors.toList());

            for (SortType sortType : listSortTypes) {
                switch (sortType) {
                    case NEW_ARRIVALS:
                        products.sort(Comparator.comparing(ProductResponse::getCreatedDate).reversed());
                    case NAME_ASC:
                        products.sort(Comparator.comparing(ProductResponse::getProductName));
                        break;
                    case NAME_DESC:
                        products.sort(Comparator.comparing(ProductResponse::getProductName).reversed());
                        break;
                    case PRICE_ASC:
                        products.sort(Comparator.comparing(ProductResponse::getPrice));
                        break;
                    case PRICE_DESC:
                        products.sort(Comparator.comparing(ProductResponse::getPrice).reversed());
                        break;
                    case RATING:
                        products.sort(Comparator.comparing(p -> getAverageRating(p.getProductId())));
                    default:
                        break;
                }
            }
        }
        long totalProducts = products.size();
        long totalPages =  (long) Math.ceil(totalProducts * 1.0 / pageSize);

        products = products.stream().skip(page * pageSize).limit(pageSize).collect(Collectors.toList());

        ProductPaging result = new ProductPaging();
        result.setTotalPages((int)totalPages);
        result.setProducts(listMapper.mapList(products, new ProductResponse()));
        return result;

    }

    private List<ImageResponse> getImagesByProductId(int productId) {
        List<Image> images;
        images = imageRepo.findAll().stream().filter(i->i.getProduct().getProductId().equals(productId)).collect(Collectors.toList());
        return listMapper.mapList(images, new ImageResponse());
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

    @Override
    public List<ProductResponse> findTop8ByOrderByCreatedDateDesc() {
        return (List<ProductResponse>) listMapper.mapList(productRepo.findTop8ByOrderByCreatedDateDesc(), new ProductResponse());
    }

    @Override
    public List<ProductResponse> findTop8ByOrderByQuantityDesc() {
        return (List<ProductResponse>) listMapper.mapList(productRepo.findTop8ByOrderByInventoryCountDesc(), new ProductResponse());
    }

    @Override
    public List<ProductResponse> findTop8ByOrderByDiscounts() {
        return (List<ProductResponse>) listMapper.mapList(productRepo.findTop8ByOrderByDiscounts(), new ProductResponse());
    }
}


