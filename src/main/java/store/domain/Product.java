package store.domain;

public class Product {
    private String name;
    private int price;
    private int quantity;
    // 프로모션의 이름이 null인 경우 view에서 적절하게 처리할 수 있도록 수정
    private String promotionName;

    public Product(String name, int price, int quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
