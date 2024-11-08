package store.utils;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductParserTest {

    ProductParser productParser = new ProductParser();

    @Test
    public void 구매할_상품의_이름과_수량을_분리() {
        //given
        String input = "[사이다-2],[감자칩-1]";

        //when
        Map<String, Integer> result = productParser.buyParser(input);

        //then
        Assertions.assertThat(result.get("사이다")).isEqualTo(2);
        Assertions.assertThat(result.get("감자칩")).isEqualTo(1);
    }
}