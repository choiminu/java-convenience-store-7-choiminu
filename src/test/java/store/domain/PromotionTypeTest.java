package store.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static store.domain.PromotionType.BUY_ONE_GET_ONE_FREE;
import static store.domain.PromotionType.BUY_TWO_GET_ONE_FREE;
import static store.domain.PromotionType.NONE;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PromotionTypeTest {

    @Test
    public void 주어진_수량에_해당하는_프로모션_타입을_반환한다_투_플러스_원() {
        //given
        int requiredQuantity = 2;
        int bonusQuantity = 1;

        //when
        PromotionType promotionType = PromotionType.findByPromotionType(requiredQuantity, bonusQuantity);

        //then
        assertEquals(promotionType, BUY_TWO_GET_ONE_FREE);
    }

    @Test
    public void 주어진_수량에_해당하는_프로모션_타입을_반환한다_원_플러스_원() {
        //given
        int requiredQuantity = 1;
        int bonusQuantity = 1;

        //when
        PromotionType promotionType = PromotionType.findByPromotionType(requiredQuantity, bonusQuantity);

        //then
        assertEquals(promotionType, BUY_ONE_GET_ONE_FREE);
    }

    @ParameterizedTest
    @CsvSource({
            "7, 2",
            "5, 1",
            "10, 3",
            "2, 0"
    })
    public void 현재_수량을고려하여_프로모션으로_받을수있는_무료_상품의개수를_반환한다_투_플러스_원(int currentStock, int expectedBonus) {
        //given when
        int result = BUY_TWO_GET_ONE_FREE.calculateBonusQuantity(currentStock);

        //then
        assertEquals(result, expectedBonus);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 0",
            "1, 0"
    })
    public void 프로모션의_재고가_부족하면_무료_상품0개를_반환한다_투_플러스_원(int currentStock, int expectedBonus) {
        //given when
        int result = BUY_TWO_GET_ONE_FREE.calculateBonusQuantity(currentStock);

        //then
        assertEquals(result, expectedBonus);
    }

    @ParameterizedTest
    @CsvSource({
            "7, 3",
            "5, 2",
            "10, 5",
            "2, 1"
    })
    public void 현재_수량을고려하여_프로모션으로_받을수있는_무료_상품의개수를_반환한다_원_플러스_원(int currentStock, int expectedBonus) {
        //given when
        int result = BUY_ONE_GET_ONE_FREE.calculateBonusQuantity(currentStock);

        //then
        assertEquals(result, expectedBonus);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0"
    })
    public void 프로모션의_재고가_부족하면_무료_상품0개를_반환한다_원_플러스_원(int currentStock, int expectedBonus) {
        //given when
        int result = BUY_TWO_GET_ONE_FREE.calculateBonusQuantity(currentStock);

        //then
        assertEquals(result, expectedBonus);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0"
    })
    public void 프로모션의_재고가_부족하면_0을_반환한다_일반_재고(int currentStock, int expectedBonus) {
        //given when
        int result = NONE.calculateBonusQuantity(currentStock);

        //then
        assertEquals(result, expectedBonus);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1",
            "5, 1",
            "7, 1"
    })
    public void 고객이_프로모션_적용_수량을_안가져온_경우_필요한_수량을_반환한다_투_플러스_원(int orderQuantity, int expectedQuantity) {
        // given when
        int result = BUY_TWO_GET_ONE_FREE.calculateRequiredQuantityForPromotion(orderQuantity);

        //then
        assertEquals(result, expectedQuantity);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 0",
            "3, 1",
    })
    public void 고객이_프로모션_적용_수량을_안가져온_경우_필요한_수량을_반환한다_원_플러스_원(int orderQuantity, int expectedQuantity) {
        // given when
        int result = BUY_ONE_GET_ONE_FREE.calculateRequiredQuantityForPromotion(orderQuantity);

        //then
        assertEquals(expectedQuantity, result);
    }

}