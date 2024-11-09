package store.domain;

public class StockQuantity {
    private int generalStockQuantity;
    private int promotionStockQuantity;
    private final boolean isPromotion;

    public StockQuantity(int quantity, String promotion) {
        this.isPromotion = Promotion.isPromotionProduct(promotion);
        setStockQuantity(quantity);
    }

    private void setStockQuantity(int quantity) {
        if (isPromotion) {
            this.promotionStockQuantity = quantity;
            return;
        }
        this.generalStockQuantity = quantity;
    }

    public int getStockQuantity() {
        if (isPromotion) {
            return promotionStockQuantity;
        }
        return generalStockQuantity;
    }

}
