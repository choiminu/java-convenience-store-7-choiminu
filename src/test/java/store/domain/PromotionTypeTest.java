package store.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static store.domain.PromotionType.BUY_ONE_GET_ONE_FREE;
import static store.domain.PromotionType.BUY_TWO_GET_ONE_FREE;

import org.junit.jupiter.api.Test;

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

}