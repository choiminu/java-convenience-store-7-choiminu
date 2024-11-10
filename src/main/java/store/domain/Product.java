package store.domain;

public class Product {
    private String name;
    private int price;
    private int stock;
    private String promotionName; // TODO 프로모션의 이름이 null인 경우 view에서 적절하게 처리할 수 있도록 수정

    public Product(String name, int price, int stock, String promotionName) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.promotionName = promotionName;
    }

    public void addStock(int stock) {
        this.stock += stock;
    }

    public void removeStock(int stock) {
        int remainingStock = this.stock - stock;

        if (remainingStock < 0) {
            throw new IllegalArgumentException("재고가 부족합니다."); // TODO 예외 메세지 수정
        }

        this.stock = remainingStock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
