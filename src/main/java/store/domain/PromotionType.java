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

    public static PromotionType findByPromotionType(int requiredQuantity, int bonusQuantity) {
        for (PromotionType promotionType : PromotionType.values()) {
            if (promotionType.requiredQuantity == requiredQuantity
                    && promotionType.bonusQuantity == bonusQuantity) {
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
            return calculateMissingQuantityOne(orderQuantity);
        }

        return calculateMissingQuantityTwo(orderQuantity);
    }

    private int calculateMissingQuantityOne(int orderQuantity) {
        if (orderQuantity == requiredQuantity || orderQuantity % 2 != 0) {
            return bonusQuantity;
        }
        return 0;
    }

    private int calculateMissingQuantityTwo(int orderQuantity) {
        if (orderQuantity == requiredQuantity) {
            return bonusQuantity;
        }
        if (orderQuantity % requiredQuantity != 0 && orderQuantity % (requiredQuantity + bonusQuantity) != 0) {
            return requiredQuantity - (orderQuantity % requiredQuantity);
        }
        return 0;
    }


}
