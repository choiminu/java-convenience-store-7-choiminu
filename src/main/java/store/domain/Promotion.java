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

    // 이름으로 프로모션을 검색한다.
    public static Promotion findByName(String name) {
        return Arrays.stream(Promotion.values())
                .filter(promotionEnum -> promotionEnum.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    // 프로모션의 전체 정보를 가지고 프로모션을 검색한다.
    public static Promotion findPromotion(String name, int requiredQuantity, int freeQuantity, String startDate,
                                          String endDate) {
        for (Promotion promotion : values()) {
            if (promotion.getName().equals(name) && promotion.getRequiredQuantity() == requiredQuantity &&
                    promotion.getFreeQuantity() == freeQuantity && promotion.getStartDate().equals(startDate) &&
                    promotion.getEndDate().equals(endDate)) {
                return promotion;
            }
        }
        throw new IllegalArgumentException("조건에 맞는 프로모션이 없습니다.");
    }

    // 프로모션의 날짜가 유효한지 검사한다.
    public boolean isDateValid() {
        LocalDateTime today = DateTimes.now();
        return !today.isBefore(startDate.atStartOfDay()) && !today.isAfter(endDate.atStartOfDay());
    }

    //  Buy N Get Free 기능
    public int calculateFreeItems(int quantity, int promoStock) {
        if (!isDateValid()) {
            return 0;
        }
        int eligibleSets = quantity / requiredQuantity;
        return Math.min(eligibleSets * freeQuantity, promoStock);
    }

    public int calculateAdditionalPromoItems(int quantity) {
        if (quantity % requiredQuantity == 0) {
            return freeQuantity;
        }
        return 0;
    }

    // 프로모션 혜택을 받기 위해 추가로 필요한 아이템 수를 계산한다.
// 주어진 수량(quantity)이 프로모션 단위의 배수가 아닐 경우 필요한 추가 수량을 반환한다.
    public int additionalItemsNeeded(int quantity) {
        int remainder = quantity % requiredQuantity;
        return remainder == 0 ? 0 : requiredQuantity - remainder;
    }


    // 프로모션 재고가 부족한 경우 혜택을 받지 못하는 아이템 수를 계산한다.
    // 주어진 수량(quantity)과 프로모션 재고(promoStock)를 기반으로 혜택을 받지 못하는 개수를 반환한다.
    public int itemsWithoutPromo(int quantity, int promoStock) {
        int eligibleSets = quantity / requiredQuantity; // 구매한 수량으로 충족되는 세트 수
        int maxFreeItems = eligibleSets * freeQuantity; // 받을 수 있는 최대 무료 아이템 수

        // 프로모션 재고가 부족할 경우, 부족한 무료 아이템 수 계산
        return (maxFreeItems + quantity) > promoStock ? (maxFreeItems + quantity) - promoStock : 0;
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
}
