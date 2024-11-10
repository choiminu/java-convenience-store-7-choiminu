package store.domain;

public enum PromotionType {
    BUY_TWO_GET_ONE_FREE(2, 1),
    BUY_ONE_GET_ONE_FREE(1, 1),
    NONE(0, 0);

    private final int requiredQuantity;
    private final int bonusQuantity;

    PromotionType(int requiredQuantity, int bonusQuantity) {
        this.requiredQuantity = requiredQuantity;
        this.bonusQuantity = bonusQuantity;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public int getBonusQuantity() {
        return bonusQuantity;
    }

    public static PromotionType findByPromotionType(int requiredQuantity, int bonusQuantity) {
        for (PromotionType promotionType : PromotionType.values()) {
            if (promotionType.getRequiredQuantity() == requiredQuantity
                    && promotionType.getBonusQuantity() == bonusQuantity) {
                return promotionType;
            }
        }
        return NONE;
    }

    public int calculateBonusQuantity(int currentStock) {
        if (this == NONE) {
            return 0;
        }
        if (currentStock < requiredQuantity) {
            return 0;
        }
        return currentStock / (requiredQuantity + bonusQuantity);
    }

    public int calculateRequiredQuantityForPromotion(int orderQuantity) {
        if (this == NONE) {
            return 0;
        }

        if (this == BUY_ONE_GET_ONE_FREE) {
            return handleBuyOneGetOneFree(orderQuantity);
        }

        return calculateMissingQuantity(orderQuantity);
    }

    private int handleBuyOneGetOneFree(int orderQuantity) {
        if (orderQuantity == requiredQuantity || orderQuantity % 2 != 0) {
            return bonusQuantity;
        }
        return 0;
    }

    private int calculateMissingQuantity(int orderQuantity) {
        int missingQuantity = requiredQuantity - (orderQuantity % requiredQuantity);
        if (orderQuantity == requiredQuantity) {
            return bonusQuantity;
        }
        if (orderQuantity % requiredQuantity != 0) {
            return missingQuantity;
        }
        return 0;
    }


}
