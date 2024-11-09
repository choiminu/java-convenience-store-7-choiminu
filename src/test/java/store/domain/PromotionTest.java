package store.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @Test
    void 이름으로_프로모션_검색() {
        //given
        String promotionName = "탄산2+1";

        //when
        Promotion promotion = Promotion.findByName(promotionName);

        //then
        Assertions.assertThat(promotion).isEqualTo(Promotion.BUY_TWO_GET_ONE);
    }

    @Test
    void 이름으로_프로모션_검색_실패() {
        //given
        String promotionName = "프로모션없음";

        //when
        Promotion promotion = Promotion.findByName(promotionName);

        //then
        Assertions.assertThat(promotion).isEqualTo(null);
    }

    @Test
    void 모든_정보로_프로모션_검색() {
        //given
        String promotionName = "탄산2+1";
        int requiredQuantity = 2;
        int freeQuantity = 1;
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        //when
        Promotion promotion = Promotion.findPromotion(promotionName, requiredQuantity, freeQuantity, startDate,
                endDate);

        //then
        Assertions.assertThat(promotion).isEqualTo(Promotion.BUY_TWO_GET_ONE);
    }

    @Test
    void 모든_정보로_프로모션_검색_실패() {
        //given
        String promotionName = "프로모션없음";
        int requiredQuantity = 2;
        int freeQuantity = 1;
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";

        // when then
        Assertions.assertThatThrownBy(
                () -> Promotion.findPromotion(promotionName, requiredQuantity, freeQuantity, startDate,
                        endDate)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 현재날짜와_프로모션_기간이_유효한지_확인() {
        //given
        Promotion buyTwoGetOne = Promotion.BUY_TWO_GET_ONE;

        //when
        boolean result = buyTwoGetOne.isDateValid();

        //then
        assertTrue(result);
    }

    @Test
    void 현재날짜와_프로모션_기간이_유효하지_않으면_실패() {
        //given
        Promotion buyTwoGetOne = Promotion.BLACK_FRIDAY;

        //when
        boolean result = buyTwoGetOne.isDateValid();

        //then
        assertFalse(result);
    }

    @Test
    void 투플러스원_프로모션_적용() {
        // given
        Promotion promotion = Promotion.BUY_TWO_GET_ONE;
        int quantity = 6;
        int promoStock = 10;

        // when
        int freeItems = promotion.calculateFreeItems(quantity, promoStock);

        // then
        assertEquals(3, freeItems); // 6개 구매 시, 2+1 조건으로 3개의 무료 증정
    }

    @Test
    void 프로모션_적용이_가능한_상품에_대해_고객이_해당_수량만큼_가져오지_않았을_경우_수량_반환() {
        // given
        Promotion promotion = Promotion.BUY_TWO_GET_ONE;
        int quantity = 2;

        // when
        int additionalItems = promotion.calculateAdditionalPromoItems(quantity);

        // then
        assertEquals(1, additionalItems);
    }

    @Test
    void 프로모션_재고가_충분한_경우_혜택_미적용_아이템_없음() {
        // given
        Promotion promotion = Promotion.BUY_TWO_GET_ONE;
        int quantity = 6; // 2+1 조건에 맞는 수량
        int promoStock = 9; // 재고가 충분한 상황

        // when
        int itemsWithoutPromo = promotion.itemsWithoutPromo(quantity, promoStock);

        // then
        assertEquals(0, itemsWithoutPromo); // 모든 혜택이 적용되므로 혜택 미적용 아이템은 없음
    }

    @Test
    void 프로모션_재고가_부족하여_일부_혜택_미적용() {
        // given
        Promotion promotion = Promotion.BUY_TWO_GET_ONE;
        int quantity = 6; // 2+1 조건에 맞는 수량
        int promoStock = 8; // 필요한 기본 수량 + 최대 무료 아이템 수를 충족하지 못하는 재고

        // when
        int itemsWithoutPromo = promotion.itemsWithoutPromo(quantity, promoStock);

        // then
        assertEquals(1, itemsWithoutPromo); // 기본 6개 + 무료 3개 중 1개 부족
    }

    @Test
    void 프로모션_혜택을_받기_위한_추가_아이템_수_계산() {
        // given
        Promotion promotion = Promotion.BUY_TWO_GET_ONE;
        int quantity = 3; // 프로모션 혜택을 받기에는 부족한 수량

        // when
        int additionalItems = promotion.additionalItemsNeeded(quantity);

        // then
        assertEquals(1, additionalItems); // 2+1 조건에서 추가로 필요한 수량은 1개
    }

}