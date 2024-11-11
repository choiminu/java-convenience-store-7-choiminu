package store.service;

import java.util.List;
import java.util.Map;
import store.domain.Product;
import store.domain.Promotion;
import store.utils.loader.ProductLoader;

public class ProductService {

    private static final String filePath = "src/main/resources/products.md";
    private static final List<Product> products = ProductLoader.loadProductsFromFile(filePath);

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

    public void checkQuantity(Map<String, Integer> shoppingCart) {

        int currentStock = 0;

        for (String name : shoppingCart.keySet()) {
            int stock = shoppingCart.get(name);

            List<Product> productByName = findProductByName(name);

            for (Product product : productByName) {
                currentStock += product.getStock();
            }

            if (stock > currentStock) {
                throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
        }


    }

    public void checkIfProductIsAvailable(String name) {
        products.stream()
                .filter(p -> p.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다. 다시 입력해 주세요."));

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
                .filter(product -> product.getName().equals(name) && "null".equals(product.getPromotionName()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다. 다시 입력해 주세요."));
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
