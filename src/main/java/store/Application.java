package store;


import java.util.List;
import store.domain.Product;
import store.utils.loader.ProductLoader;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        List<Product> products = ProductLoader.loadProductsFromFile("src/main/resources/products.md");

        outputView.printWelcomeMessage();
        outputView.printOwnedProducts(products);
    }
}
