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

    public int removeStock(int stock) {
        int remainingStock = this.stock - stock;
        if (remainingStock < 0 && promotionName.equals("null")) {
            throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
        if (remainingStock > 0) {
            return this.stock = remainingStock;
        }
        return this.stock = 0;
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
