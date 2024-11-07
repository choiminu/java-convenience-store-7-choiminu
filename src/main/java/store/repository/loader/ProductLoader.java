package store.repository.loader;

import java.util.ArrayList;
import java.util.List;
import store.domain.product.Product;
import store.utils.FileReadHelper;
import store.utils.ProductParser;

public class ProductLoader implements Loader<List<Product>> {

    private final ProductParser parseProduct;
    private final FileReadHelper fileReadHelper;

    public ProductLoader(ProductParser parseProduct, FileReadHelper readHelper) {
        this.parseProduct = parseProduct;
        this.fileReadHelper = readHelper;
    }

    @Override
    public List<Product> load(String path) {
        List<Product> products = new ArrayList<>();
        List<String> lines = fileReadHelper.readLines(path);

        for (String line : lines) {
            products.add(parseProduct.parseProduct(line));
        }

        return products;
    }
}
