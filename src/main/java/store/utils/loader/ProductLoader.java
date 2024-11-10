package store.utils.loader;

import java.util.ArrayList;
import java.util.List;
import store.domain.Product;

public class ProductLoader {
    private final static String DELIMITER = ",";
    private final static int PRODUCT_NAME_INDEX = 0;
    private final static int PRODUCT_PRICE_INDEX = 1;
    private final static int PRODUCT_QUANTITY_INDEX = 2;
    private final static int PRODUCT_PROMOTION_NAME_INDEX = 3;

    public static List<Product> loadProductsFromFile(String filePath) {
        List<Product> products = new ArrayList<>();
        List<String> fileLines = FileLoader.loadFile(filePath);

        for (String fileLine : fileLines) {
            parseProduct(products, fileLine);
        }

        return products;
    }

    private static void parseProduct(List<Product> products, String fileLine) {
        String[] parts = fileLine.split(DELIMITER);
        products.add(new Product(parts[PRODUCT_NAME_INDEX],
                Integer.parseInt(parts[PRODUCT_PRICE_INDEX]),
                Integer.parseInt(parts[PRODUCT_QUANTITY_INDEX]),
                parts[PRODUCT_PROMOTION_NAME_INDEX]));
    }
}
