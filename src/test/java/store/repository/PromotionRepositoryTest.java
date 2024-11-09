package store.repository;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.domain.Promotion;
import store.utils.PromotionLoader;

class PromotionRepositoryTest {
    PromotionLoader promotionLoader = new PromotionLoader("src/main/resources/promotions.md");
    PromotionRepository promotionRepository = new PromotionRepository(promotionLoader);

    @Test
    public void 모든_프로모션_조회() {
        // given & when
        List<Promotion> promotions = promotionRepository.findAll();

        // then
        Assertions.assertThat(promotions).isNotNull();
        Assertions.assertThat(promotions.size()).isGreaterThan(0);
        System.out.println("모든 프로모션: " + promotions);
    }

    @ParameterizedTest
    @CsvSource({
            "탄산2+1, true",
            "MD추천상품, true",
            "존재하지않는프로모션, false"
    })
    public void 특정_프로모션_이름으로_조회(String promotionName, boolean exists) {
        // when
        Promotion promotion = promotionRepository.findByName(promotionName);

        // then
        if (exists) {
            Assertions.assertThat(promotion).isNotNull();
            Assertions.assertThat(promotion.getName()).isEqualTo(promotionName);
        } else {
            Assertions.assertThat(promotion).isNull();
        }
    }
}