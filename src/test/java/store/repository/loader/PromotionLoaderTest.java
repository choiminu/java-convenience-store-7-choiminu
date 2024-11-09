package store.repository.loader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.domain.Promotion;
import store.utils.FileReadHelper;
import store.utils.PromotionParser;

class PromotionLoaderTest {

    private static final String FILE_PATH = "src/main/resources/promotions.md";

    @Test
    public void 프로모션_정보_가져오기() {
        //given
        PromotionLoader promotionLoader = new PromotionLoader(new PromotionParser(), new FileReadHelper());

        //when
        List<Promotion> promotions = promotionLoader.load(FILE_PATH);

        //then
        assertThat(promotions)
                .extracting(Promotion::getName, Promotion::getRequiredQuantity, Promotion::getFreeQuantity,
                        Promotion::getStartDate, Promotion::getEndDate)
                .containsExactly(
                        tuple("탄산2+1", 2, 1, "2024-01-01", "2024-12-31"),
                        tuple("MD추천상품", 1, 1, "2024-01-01", "2024-12-31"),
                        tuple("반짝할인", 1, 1, "2024-11-01", "2024-11-30"),
                        tuple("블랙프라이데이", 1, 1, "2024-11-29", "2024-11-29"));

    }

}