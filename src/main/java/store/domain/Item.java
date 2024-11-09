package store.domain;

public class Item {
    private String itemName;
    private int price;
    private int quantity;
    private Promotion promotion;

    public Item(String itemName, int price, int quantity, String promotion) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.promotion = Promotion.findByName(promotion);
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
