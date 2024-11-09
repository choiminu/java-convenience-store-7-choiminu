package store.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public enum Promotion {
    BUY_TWO_GET_ONE("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")),
    MD_RECOMMENDED("MD추천상품", 1, 1, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")),
    FLASH_DISCOUNT("반짝할인", 1, 1, LocalDate.parse("2024-11-01"), LocalDate.parse("2024-11-30")),
    BLACK_FRIDAY("블랙프라이데이", 1, 1, LocalDate.parse("2024-11-29"), LocalDate.parse("2024-11-29"));

    private final String name;
    private final int requiredQuantity;
    private final int freeQuantity;
    private final LocalDate startDate;
    private final LocalDate endDate;

    Promotion(String name, int requiredQuantity, int freeQuantity, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.requiredQuantity = requiredQuantity;
        this.freeQuantity = freeQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion findByName(String name) {
        return Arrays.stream(Promotion.values())
                .filter(promotionEnum -> promotionEnum.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean isDateValid() {
        LocalDateTime today = DateTimes.now();
        return !today.isBefore(startDate.atStartOfDay()) && !today.isAfter(endDate.atStartOfDay());
    }


    public int calculateFreeItems(int quantity, int promoStock) {
        if (!isDateValid()) {
            return 0;
        }
        int eligibleSets = quantity / requiredQuantity;
        return Math.min(eligibleSets * freeQuantity, promoStock);
    }

    public String getName() {
        return name;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }

    public String getStartDate() {
        return String.valueOf(startDate);
    }

    public String getEndDate() {
        return String.valueOf(endDate);
    }

    public static Promotion findPromotion(String name, int requiredQuantity, int freeQuantity, String startDate,
                                          String endDate) {
        for (Promotion promotion : values()) {
            if (promotion.getName().equals(name) &&
                    promotion.getRequiredQuantity() == requiredQuantity &&
                    promotion.getFreeQuantity() == freeQuantity &&
                    promotion.getStartDate().equals(startDate) &&
                    promotion.getEndDate().equals(endDate)) {
                return promotion;
            }
        }
        throw new IllegalArgumentException("조건에 맞는 프로모션이 없습니다.");
    }
}
