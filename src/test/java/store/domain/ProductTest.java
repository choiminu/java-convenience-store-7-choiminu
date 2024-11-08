package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    public void 상품_생성() {
        //given
        String name = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = null;

        //when
        Product product = Product.createProduct(name, price, quantity, promotion);

        //then
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getStockQuantity()).isEqualTo(quantity);
        assertThat(product.getPromotion()).isEqualTo(promotion);
    }

    @Test
    public void 상품_재고_증가() {
        //given
        String name = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = null;
        Product product = Product.createProduct(name, price, quantity, promotion);

        //when
        product.addStock(1);

        //then
        assertThat(product.getStockQuantity()).isEqualTo(11);
    }

    @Test
    public void 상품_재고_감소() {
        //given
        String name = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = null;
        Product product = Product.createProduct(name, price, quantity, promotion);

        //when
        product.removeStock(1);

        //then
        assertThat(product.getStockQuantity()).isEqualTo(9);
    }

    @Test
    public void 상품_재고_부족_예외발생() {
        //given
        String name = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = null;
        Product product = Product.createProduct(name, price, quantity, promotion);

        //when
        Assertions.assertThatThrownBy(() -> product.removeStock(11));
    }
}