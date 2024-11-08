package store.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.domain.product.Product;
import store.repository.loader.ProductLoader;

public class ProductRepository {
    private final ProductLoader loader;
    private static final Map<Long, Product> products = new HashMap<>();
    private static Long sequence = 0L;

    public ProductRepository(ProductLoader loader) {
        this.loader = loader;
        initializeProducts();
    }

    private void initializeProducts() {
        List<Product> loadedProducts = loader.load("src/main/resources/products.md");
        for (Product product : loadedProducts) {
            addProductWithId(product);
        }
    }

    private void addProductWithId(Product product) {
        Long id = sequence++;
        product.setId(id);
        products.put(id, product);
    }

    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }
}
