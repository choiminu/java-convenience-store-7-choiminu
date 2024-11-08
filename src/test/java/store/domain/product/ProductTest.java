package store.domain.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    public void 재고확인() {
        //given
        Product product1 = Product.createProduct("콜라", 1000, 10, null);
        Product product2 = Product.createProduct("콜라", 1000, 10, "행사 상품");

        //when
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2));

        //then
        Assertions.assertDoesNotThrow(() -> Product.canPurchase(products, 10));
    }

    @Test
    public void 재고확인_예외() {
        //given
        Product product1 = Product.createProduct("콜라", 1000, 10, null);
        Product product2 = Product.createProduct("콜라", 1000, 10, "행사 상품");

        //when
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2));

        //then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> Product.canPurchase(products, 30));
    }
}