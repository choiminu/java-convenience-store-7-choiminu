package store.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {
    @Test
    void 상품_생성() {
        //given
        String itemName = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = "탄산2+1";

        //when
        Item item = new Item(itemName, price, quantity, promotion);

        //then
        assertThat(item.getItemName()).isEqualTo(itemName);
        assertThat(item.getPrice()).isEqualTo(price);
        assertThat(item.getQuantity()).isEqualTo(quantity);
        assertThat(item.getPromotion()).isEqualTo(Promotion.BUY_TWO_GET_ONE);
    }

    @Test
    void 상품_재고_증가() {
        //given
        String itemName = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = "탄산2+1";
        Item item = new Item(itemName, price, quantity, promotion);

        //when
        item.addStock(3);

        //then
        assertThat(item.getQuantity()).isEqualTo(13);
    }

    @Test
    void 상품_재고_감소() {
        //given
        String itemName = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = "탄산2+1";
        Item item = new Item(itemName, price, quantity, promotion);

        //when
        item.removeStock(10);

        //then
        assertThat(item.getQuantity()).isEqualTo(0);
    }

    @Test
    void 일반_상품_재고_감소_실패() {
        //given
        String itemName = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = "프로모션적용x";
        Item item = new Item(itemName, price, quantity, promotion);

        //when
        Assertions.assertThatThrownBy(() -> item.removeStock(13))
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로모션_상품_재고_감소_실패() {
        //given
        String itemName = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = "탄산2+1";
        Item item = new Item(itemName, price, quantity, promotion);

        //when
        int remainStock = item.removeStock(13);

        //then
        assertThat(remainStock).isEqualTo(3);
    }



}