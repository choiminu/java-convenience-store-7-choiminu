package store.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProduct {
    private List<Product> products;
    private int orderPrice;
    private Map<Product, Integer> count = new HashMap<>();

    private OrderProduct() {
    }

    public OrderProduct(List<Product> products, int orderPrice, Map<Product, Integer> count) {
        this.products = products;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderProduct createOrderProduct(List<Product> products, int orderCount) {
        Map<Product, Integer> orderCounts = new HashMap<>();
        Product promotionProduct = findPromotionProduct(products);
        int remainCount = orderCount;
        int before = promotionProduct.getStock();
        int insufficientStock = promotionProduct.removeStock(orderCount);
        remainCount -= before - insufficientStock;

        if (remainCount > 0) {
            handleInsufficientStock(products, remainCount, insufficientStock);
            Product generalProduct = findGenaralProduct(products);
            orderCounts.put(generalProduct, remainCount);
        }

        orderCounts.put(promotionProduct, before - insufficientStock);
        return new OrderProduct(products, promotionProduct.getPrice(), orderCounts);
    }

    private static void handleInsufficientStock(List<Product> products, int orderCount, int insufficientStock) {
        Product generalProduct = findGenaralProduct(products);
        generalProduct.removeStock(orderCount - insufficientStock);
    }

    private static Product findPromotionProduct(List<Product> products) {
        return products.stream()
                .filter(product -> !product.getPromotionName().equals("null"))
                .findFirst()
                .orElse(products.getFirst());
    }

    private static Product findGenaralProduct(List<Product> products) {
        return products.stream()
                .filter(product -> product.getPromotionName().equals("null"))
                .findFirst()
                .orElse(products.getFirst());
    }

    public void cancel() {
        products.forEach(product -> {
            Integer productCount = count.get(product);
            if (productCount != null) {
                product.addStock(productCount);
            }
        });
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public int getOrderQuantity() {
        return count.values()
                .stream()
                .mapToInt(i -> i)
                .sum();
    }


    public String getProductName() {
        return products.getFirst().getName();
    }
}
