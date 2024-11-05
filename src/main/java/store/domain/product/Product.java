package store.domain.product;

public class Product {
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

    public static Product createProduct(String name, int price, int quantity, String promotion) {
        StockQuantity stockQuantity = new StockQuantity(quantity, 0);
        if (promotion != null) {
            stockQuantity = new StockQuantity(0, quantity);
        }
        return new Product(name, price, stockQuantity, promotion);
    }
}
