package store.domain;

public class Product {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String promotion;

    private Product(String name, int price, int stockQuantity, String promotion) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.promotion = promotion;
    }

    public static Product createProduct(String name, int price, int quantity, String promotion) {
        return new Product(name, price, quantity, promotion);
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int calculatedQuantity = this.stockQuantity - quantity;

        if (calculatedQuantity < 0) {
            throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }

        this.stockQuantity = calculatedQuantity;
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
        return stockQuantity;
    }

    public String getPromotion() {
        return promotion;
    }

}
