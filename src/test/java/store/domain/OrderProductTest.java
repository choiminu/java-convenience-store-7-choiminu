package store.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderProductTest {

    @Test
    public void 일반_상품_수량_차감() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);

        // when
        OrderProduct.createOrderProduct(orderItems, 5);

        // then
        Assertions.assertEquals(5, product1.getStock());
    }

    @Test
    public void 일반_상품_주문_취소() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);

        // when
        OrderProduct orderProduct = OrderProduct.createOrderProduct(orderItems, 5);
        orderProduct.cancel();

        // then
        Assertions.assertEquals(10, product1.getStock());
    }

    @Test
    public void 프로모션_재고_충분시_프로모션에서_차감() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 5, "탄산2+1"); // 프로모션 상품
        Product product2 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);
        orderItems.add(product2);

        // when
        OrderProduct.createOrderProduct(orderItems, 15);

        // then
        Assertions.assertEquals(0, product1.getStock());
        Assertions.assertEquals(0, product2.getStock());
    }

    @Test
    public void 프로모션_재고_충분시_차감_후_취소_재고_복원() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 10, "탄산2+1"); // 프로모션 상품
        Product product2 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);
        orderItems.add(product2);

        // when
        OrderProduct orderProduct = OrderProduct.createOrderProduct(orderItems, 3);
        orderProduct.cancel();

        // then
        Assertions.assertEquals(10, product1.getStock());
        Assertions.assertEquals(10, product2.getStock());
    }

    @Test
    public void 프로모션_재고_부족시_일반에서_차감() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 0, "탄산2+1"); // 프로모션 상품
        Product product2 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);
        orderItems.add(product2);

        // when
        OrderProduct.createOrderProduct(orderItems, 5);

        // then
        Assertions.assertEquals(0, product1.getStock());
        Assertions.assertEquals(5, product2.getStock());
    }

    @Test
    public void 프로모션_재고_부족시_일반에서_차감_후_취소_재고_복원() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 0, "탄산2+1"); // 프로모션 상품
        Product product2 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);
        orderItems.add(product2);

        // when
        OrderProduct orderProduct = OrderProduct.createOrderProduct(orderItems, 5);
        orderProduct.cancel();

        // then
        Assertions.assertEquals(0, product1.getStock());
        Assertions.assertEquals(10, product2.getStock());
    }

    @Test
    public void 프로모션_및_일반_재고_부족_예외_발생() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 0, "탄산2+1"); // 프로모션 상품
        Product product2 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);
        orderItems.add(product2);

        // when
        assertThatThrownBy(() -> OrderProduct.createOrderProduct(orderItems, 11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 일반_재고_부족_예외_발생() {
        // given
        List<Product> orderItems = new ArrayList<>();
        orderItems.add(new Product("콜라", 1000, 10, "null"));

        // when
        assertThatThrownBy(() -> OrderProduct.createOrderProduct(orderItems, 15))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족합니다.");
    }

    @Test
    public void 프로모션_재고_초과_주문시_예외_발생() {
        // given
        List<Product> orderItems = new ArrayList<>();
        orderItems.add(new Product("콜라", 1000, 5, "탄산2+1")); // 프로모션 상품
        orderItems.add(new Product("콜라", 1000, 10, "null")); // 일반 상품

        // when
        assertThatThrownBy(() -> OrderProduct.createOrderProduct(orderItems, 16))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족합니다.");
    }

    @Test
    public void 주문_취소_재고_복원() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 10, "탄산2+1"); // 프로모션 상품
        Product product2 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);
        orderItems.add(product2);

        // when
        OrderProduct orderProduct = OrderProduct.createOrderProduct(orderItems, 5);
        orderProduct.cancel();

        // then
        Assertions.assertEquals(10, product1.getStock());
        Assertions.assertEquals(10, product2.getStock());
    }

    @Test
    public void 주문_수량_0_경우_재고_변화없음() {
        // given
        List<Product> orderItems = new ArrayList<>();
        Product product1 = new Product("콜라", 1000, 10, "탄산2+1"); // 프로모션 상품
        Product product2 = new Product("콜라", 1000, 10, "null"); // 일반 상품
        orderItems.add(product1);
        orderItems.add(product2);

        // when
        OrderProduct orderProduct = OrderProduct.createOrderProduct(orderItems, 0);

        // then
        Assertions.assertEquals(10, product1.getStock());
        Assertions.assertEquals(10, product2.getStock());
    }

}

