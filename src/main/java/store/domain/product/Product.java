package store.domain.product;

import java.util.List;

public class Product {
    private Long id;
    private String name;
    private int price;
    private StockQuantity stockQuantity;
    private String promotion;

    private Product(String name, int price, StockQuantity stockQuantity, String promotion) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.promotion = promotion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity.getStockQuantity();
    }


    public String getPromotion() {
        return promotion;
    }

    public static void canPurchase(List<Product> products, int stock) {
        int quantity = 0;

        for (Product product : products) {
            quantity += product.getStockQuantity();
        }

        if (quantity < stock) {
            throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public static Product createProduct(String name, int price, int quantity, String promotion) {
        StockQuantity stockQuantity = new StockQuantity(quantity, 0);
        if (promotion != null) {
            stockQuantity = new StockQuantity(0, quantity);
        }
        return new Product(name, price, stockQuantity, promotion);
    }
}
