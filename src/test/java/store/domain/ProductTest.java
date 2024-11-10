package store.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    public void 상품_재고_증가() {
        //given
        Product product = new Product("콜라", 1000, 10, "탄산2+1");

        //when
        product.addStock(1);

        //then
        assertEquals(product.getStock(), 11);
    }

    @Test
    public void 상품_재고_감소() {
        //given
        Product product = new Product("콜라", 1000, 10, "탄산2+1");

        //when
        int remainingStock = product.removeStock(1);

        //then
        assertEquals(product.getStock(), remainingStock);
    }

    @Test
    public void 프로모션이_적용된_상품의_재고를_초과하는경우_예외가_발생하지않는다() {
        //given
        Product product = new Product("콜라", 1000, 10, "탄산2+1");

        //when then
        int result = product.removeStock(11);

        //then
        assertEquals(result, -1);
    }

    @Test
    public void 주문수량이_재고를_초과하면_예외가_발생한다() {
        //given
        Product product = new Product("콜라", 1000, 10, "null");

        //when then
        Assertions.assertThatThrownBy(() -> product.removeStock(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

}