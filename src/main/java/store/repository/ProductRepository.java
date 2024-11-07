package store.repository;

import java.util.List;
import store.domain.product.Product;
import store.repository.loader.ProductLoader;

public class ProductRepository {
    private final List<Product> productList;

    public ProductRepository(ProductLoader productLoader) {
        this.productList = productLoader.load("src/main/resources/products.md");
    }

    public List<Product> findAll() {
        return productList;
    }
}
