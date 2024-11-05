package store.domain.product;

public class StockQuantity {
    private int generalStockQuantity;
    private int promotionalStockQuantity;

    public StockQuantity(int generalStockQuantity, int promotionalStockQuantity) {
        this.generalStockQuantity = generalStockQuantity;
        this.promotionalStockQuantity = promotionalStockQuantity;
    }

    public int getStockQuantity() {
        if (generalStockQuantity == 0) {
            return promotionalStockQuantity;
        }
        return generalStockQuantity;
    }
}
