package store.domain;

import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    public void 주문_상품을_두개_주문하고_검증한다() {
        //given
        Product product1 = new Product("콜라", 1000, 10, "탄산2+1");
        Product product2 = new Product("콜라", 1000, 10, "null");
    }


}