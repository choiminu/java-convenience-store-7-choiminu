package store.domain;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PromotionTest {

    @Test
    public void 유효한_프로모션_기간에_대해_검증한다() {
        //given
        Promotion promotion = new Promotion("탄산2+1", 2, 1,
                LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-12-31"));

        //when
        boolean dateValid = promotion.isDateValid();

        //then
        Assertions.assertTrue(dateValid);
    }

    @Test
    public void 유효하지_않은_프로모션_기간에_대해_검증한다() {
        //given
        Promotion promotion = new Promotion("탄산2+1", 2, 1,
                LocalDate.parse("2024-11-30"),
                LocalDate.parse("2024-12-31"));

        //when
        boolean dateValid = promotion.isDateValid();

        //then
        Assertions.assertFalse(dateValid);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 1",
            "6, 2",
            "9, 3"
    })
    public void 주어진_주문수량에_따른_투플러스원_프로모션_보너스_수량을_계산한다(int orderQuantity, int expectedQuantity) {
        //given
        Promotion promotion = new Promotion("탄산2+1", 2, 1,
                LocalDate.parse("2024-11-30"),
                LocalDate.parse("2024-12-31"));

        //when
        int promotionBonusQuantity = promotion.getPromotionBonusQuantity(orderQuantity);

        //then
        Assertions.assertEquals(expectedQuantity, promotionBonusQuantity);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "2, 1",
            "3, 1",
            "4, 2"
    })
    public void 주어진_주문수량에_따른_원플러스원_프로모션_보너스_수량을_계산한다(int orderQuantity, int expectedQuantity) {
        //given
        Promotion promotion = new Promotion("MD추천할인", 1, 1,
                LocalDate.parse("2024-11-30"),
                LocalDate.parse("2024-12-31"));

        //when
        int promotionBonusQuantity = promotion.getPromotionBonusQuantity(orderQuantity);

        //then
        Assertions.assertEquals(expectedQuantity, promotionBonusQuantity);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1",
            "3, 0",
            "5, 1",
            "7, 1"
    })
    public void 투플러스원_프로모션이_적용되지만_사용자가_부족한_수량을_들고온_경우_부족한_수량을_반환한다(int orderQuantity, int expectedQuantity) {
        //given
        Promotion promotion = new Promotion("탄산2+1", 2, 1,
                LocalDate.parse("2024-11-30"),
                LocalDate.parse("2024-12-31"));

        //when
        int promotionBonusQuantity = promotion.getMissingQuantityForPromotion(orderQuantity);

        //then
        Assertions.assertEquals(expectedQuantity, promotionBonusQuantity);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 0",
            "3, 1"
    })
    public void 원플러스원_프로모션이_적용되지만_사용자가_부족한_수량을_들고온_경우_부족한_수량을_반환한다(int orderQuantity, int expectedQuantity) {
        //given
        Promotion promotion = new Promotion("MD추천할인", 1, 1,
                LocalDate.parse("2024-11-30"),
                LocalDate.parse("2024-12-31"));

        //when
        int promotionBonusQuantity = promotion.getMissingQuantityForPromotion(orderQuantity);

        //then
        Assertions.assertEquals(expectedQuantity, promotionBonusQuantity);
    }

}