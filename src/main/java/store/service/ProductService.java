package store.service;

import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.utils.loader.ProductLoader;

public class ProductService {

    private final String filePath = "src/main/resources/products.md";
    private final List<Product> products = ProductLoader.loadProductsFromFile(filePath);

    public List<Product> findAll() {
        return products;
    }

    public Product findPromotionProductFromList(List<Product> products) {
        for (Product product : products) {
            if (!product.getPromotionName().equals("null")) {
                return product;
            }
        }
        return products.getFirst();
    }

    public List<Product> findProductByName(String itemName) {
        return findAll().stream()
                .filter(p -> p.getName().equals(itemName))
                .toList();
    }

    public Product findPromotionProductByName(String itemName) {
        return findAll().stream()
                .filter(p -> p.getName().equals(itemName) && !p.getPromotionName().equals("null"))
                .findFirst().orElse(findGeneralProductByName(itemName));
    }

    public Product findGeneralProductByName(String name) {
        return findAll().stream()
                .filter(product -> product.getName().equals(name) && product.getPromotionName().equals("null"))
                .findFirst()
                .orElse(null);
    }

    public Promotion findPromotionByProductName(List<Promotion> promotions, String name) {
        Product product = findPromotionProductByName(name);

        if (product == null) {
            product = findGeneralProductByName(name);
        }

        if (product == null) {
            return null; // 일반 제품도 찾을 수 없는 경우 null 반환
        }

        Product finalProduct = product;
        return promotions.stream()
                .filter(promotion -> promotion.getName().equals(finalProduct.getPromotionName()))
                .findFirst()
                .orElse(null);
    }


}
