package store.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public int getPromotionBonusQuantity(int currentStock) {
        return promotionType.calculateBonusQuantity(currentStock);
    }

    public int getMissingQuantityForPromotion(int orderQuantity) {
        return promotionType.calculateRequiredQuantityForPromotion(orderQuantity);
    }

    public boolean isDateValid() {
        LocalDateTime today = DateTimes.now();
        return !today.isBefore(startDate.atStartOfDay()) && !today.isAfter(endDate.atStartOfDay());
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
