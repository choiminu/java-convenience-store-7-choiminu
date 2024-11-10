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


}
