package store.domain;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}