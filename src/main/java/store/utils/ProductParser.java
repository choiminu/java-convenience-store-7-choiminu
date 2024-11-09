package store.utils;

import java.util.HashMap;
import java.util.Map;
import store.domain.Product;

public class ProductParser {

    private static final String DELIMITER = ",";
    private static final int PRODUCT_NAME_INDEX = 0;
    private static final int PRODUCT_PRICE_INDEX = 1;
    private static final int PRODUCT_QUANTITY_INDEX = 2;
    private static final int PRODUCT_PROMOTION_INDEX = 3;
    private static final String NULL = "null";

    public Product parseProduct(String line) {
        String[] fields = line.split(DELIMITER);
        String name = fields[PRODUCT_NAME_INDEX];
        int price = Integer.parseInt(fields[PRODUCT_PRICE_INDEX]);
        int quantity = Integer.parseInt(fields[PRODUCT_QUANTITY_INDEX]);
        String promotion = parsePromotionField(fields[PRODUCT_PROMOTION_INDEX]);
        return Product.createProduct(name, price, quantity, promotion);
    }

    private String parsePromotionField(String promotion) {
        if (promotion.equals(NULL)) {
            return null;
        }
        return promotion;
    }

    public static Map<String, Integer> buyParser(String input) {
        Map<String, Integer> map = new HashMap<>();
        String[] split = input.split(",");
        for (String string : split) {
            string = string.replaceAll("[\\[\\]]", "");
            String[] split1 = string.split("-");
            map.put(split1[0], Integer.parseInt(split1[1]));
        }
        return map;
    }
}
