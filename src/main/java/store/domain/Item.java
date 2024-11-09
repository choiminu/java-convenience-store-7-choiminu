package store.domain;

public class Item {
    private String itemName;
    private int price;
    private int quantity;
    private Promotion promotion;

    // 프로모션을 문자열로 받아 프로모션 클래스에서 관리한다.
    public Item(String itemName, int price, int quantity, String promotion) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.promotion = Promotion.findByName(promotion);
    }

    // 상품의 재고를 증가시킨다.
    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    // 프로모션 상품의 경우 수량이 부족한 경우 부족한 수량을 반환한다.
    public int removeStock(int quantity) {
        int resultQuantity = this.quantity - quantity;
        if (promotion == null && resultQuantity < 0) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.quantity = resultQuantity;
        return Math.abs(resultQuantity);
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
