package store.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderProductTest {

    @Test
    public void 주문_상품을_생성하고_검증한다() {
        //given
        Product product = new Product("콜라", 1000, 10, "탄산2+1");

        //when
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, 10);

        //then
        Assertions.assertEquals(orderProduct.getProduct().getName(), "콜라");
        Assertions.assertEquals(orderProduct.getProduct().getStock(), 0);
        Assertions.assertEquals(orderProduct.getTotalPrice(), 10000);
    }

    @Test
    public void 일반_상품의_경우_주문수량을_초과하는경우_예외가_발생한다() {
        //given
        Product product = new Product("콜라", 1000, 10, "null");

        //when
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> OrderProduct.createOrderProduct(product, 11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 프로모션_상품의_경우_주문수량을_초과하는경우_예외가_발생하지_않는다() {
        //given
        Product product = new Product("콜라", 1000, 10, "탄산2+1");

        //when
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, 11);

        //then
        Assertions.assertEquals(orderProduct.getProduct().getName(), "콜라");
        Assertions.assertEquals(orderProduct.getProduct().getStock(), -1);
        Assertions.assertEquals(orderProduct.getTotalPrice(), 11000);
    }

    @Test
    public void 주문을_취소하는경우_원래_재고의_수량상태로_원복한다() {
        //given
        Product product = new Product("콜라", 1000, 10, "탄산2+1");

        //when
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, 10);
        orderProduct.cancel();

        //then
        Assertions.assertEquals(orderProduct.getProduct().getName(), "콜라");
        Assertions.assertEquals(orderProduct.getProduct().getStock(), 10);
    }

}