package store.domain;

import java.time.LocalDate;

public class Promotion {
    private String name;
    private int buy;
    private int get;
    private LocalDate startDate;
    private LocalDate endDate;
    private PromotionType promotionType;

    public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionType = PromotionType.findByPromotionType(buy, get);
    }

    public int getNonPromotableQuantity(int orderQuantity, int currentStock) {
        return promotionType.calculateNonPromotableQuantity(orderQuantity, currentStock);
    }

    public int getPromotionBonusQuantity(int currentStock) {
        return promotionType.calculateBonusQuantity(currentStock);
    }

    public int getExcepedBonusQuantity(int orderQuantity) {
        return promotionType.calculateBonusQuantity(orderQuantity);
    }

    public int getMissingQuantityForPromotion(int orderQuantity) {
        return promotionType.calculateRequiredQuantityForPromotion(orderQuantity);
    }

    public boolean isDateValid() {
        LocalDate today = LocalDate.now(); // 현재 날짜만 가져오기
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
