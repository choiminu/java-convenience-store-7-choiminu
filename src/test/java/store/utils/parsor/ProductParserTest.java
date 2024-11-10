package store.utils.parsor;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ProductParserTest {

    ProductParser productParser = new ProductParser();

    @ParameterizedTest
    @CsvSource({
            "[콜라-8], 콜라, 8",
            "[사이다-2], 사이다, 2",
    })
    public void 주어진_형식으로_상품의_이름과_수량을_파싱할_수_있다(String input, String itemName, int itemQuantity) {

        //when
        Map<String, Integer> result = productParser.parseProductQuantities(input);

        //then
        Assertions.assertTrue(result.containsKey(itemName));
        Assertions.assertEquals(result.get(itemName), itemQuantity);
    }

    @Test
    public void 여러_상품_입력_시_상품명과_수량을_정상적으로_파싱한다() {
        //given
        String input = "[사이다-2],[감자칩-1],[콜라-10]";

        //when
        Map<String, Integer> result = productParser.parseProductQuantities(input);

        //then
        Assertions.assertTrue(result.containsKey("사이다"));
        Assertions.assertEquals(result.get("사이다"), 2);

        Assertions.assertTrue(result.containsKey("감자칩"));
        Assertions.assertEquals(result.get("감자칩"), 1);

        Assertions.assertTrue(result.containsKey("콜라"));
        Assertions.assertEquals(result.get("콜라"), 10);
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라-10",
            "[콜라-10",
            "콜라-10]",
            "[콜라--10]",
            "[--10]",
            "[[치킨-2]]",
            "[치킨-2],,[사이다-2]",
            "[[치킨-2],[사이다-2]",
            "[치킨-2],[사이다-2]]",
            "[[치킨-2],[사이다-2]]",
            "[콜라]",
            "[콜라-]",
            "[콜라--]",
            "[사이다-abc]",
            "콜라--10",
            "[[콜라-10]]",
            "[콜라-10][사이다-5]",
            "[[사이다-2]][[콜라-10]]",
    })
    public void 잘못된_형식의_입력에_대해_예외가_발생한다(String input) {
        //when
        assertThatThrownBy(() -> productParser.parseProductQuantities(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}