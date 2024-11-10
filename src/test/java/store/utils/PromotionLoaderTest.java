package store.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import store.domain.Promotion;

class PromotionLoaderTest {

    String filePath = "src/main/resources/promotions.md";
    List<Promotion> promotionList = PromotionLoader.loadPromotionsFromFile(filePath);

    @Test
    public void 파일에서_프로모션_목록을_읽어_프로모션_리스트로반환한다() {
        assertEquals(promotionList.size(), 3);
    }

    @Test
    public void 첫_번째_프로모션_검증() {
        assertEquals(promotionList.get(0).getName(), "탄산2+1");
        assertEquals(promotionList.get(0).getBuy(), 2);
        assertEquals(promotionList.get(0).getGet(), 1);
        assertEquals(promotionList.get(0).getStartDate(), LocalDate.parse("2024-01-01"));
        assertEquals(promotionList.get(0).getEndDate(), LocalDate.parse("2024-12-31"));
    }

    @Test
    public void 두_번째_프로모션_검증() {
        assertEquals(promotionList.get(1).getName(), "MD추천상품");
        assertEquals(promotionList.get(1).getBuy(), 1);
        assertEquals(promotionList.get(1).getGet(), 1);
        assertEquals(promotionList.get(1).getStartDate(), LocalDate.parse("2024-01-01"));
        assertEquals(promotionList.get(1).getEndDate(), LocalDate.parse("2024-12-31"));
    }

    @Test
    public void 세_번째_프로모션_검증() {
        assertEquals(promotionList.get(2).getName(), "반짝할인");
        assertEquals(promotionList.get(2).getBuy(), 1);
        assertEquals(promotionList.get(2).getGet(), 1);
        assertEquals(promotionList.get(2).getStartDate(), LocalDate.parse("2024-11-01"));
        assertEquals(promotionList.get(2).getEndDate(), LocalDate.parse("2024-11-30"));
    }
}