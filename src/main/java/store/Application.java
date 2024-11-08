package store;

import java.util.List;
import store.domain.product.Product;
import store.repository.ProductRepository;
import store.repository.loader.ProductLoader;
import store.service.ProductService;
import store.utils.FileReadHelper;
import store.utils.ProductParser;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ProductParser productParser = new ProductParser();
        ProductLoader productLoader = new ProductLoader(productParser, new FileReadHelper());
        ProductRepository productRepository = new ProductRepository(productLoader);
        ProductService productService = new ProductService(productRepository);
        OutputView outputView = new OutputView();

        List<Product> allProduct = productService.findAllProduct();
        outputView.printProducts(allProduct);

    }
}
