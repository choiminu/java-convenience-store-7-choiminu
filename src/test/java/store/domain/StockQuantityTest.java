package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StockQuantityTest {

    @Test
    public void 프로모션_상품인경우_프로모션_재고가_증가한다() {
        //given
        int quantity = 10;
        String promotion = "탄산2+1";

        //when
        StockQuantity stockQuantity = new StockQuantity(quantity, promotion);

        //then
        assertThat(stockQuantity.getStockQuantity()).isEqualTo(quantity);
    }

    @Test
    public void 프로모션_상품이아닐경우_일반_재고가_증가한다() {
        //given
        int quantity = 10;
        String promotion = "";

        //when
        StockQuantity stockQuantity = new StockQuantity(quantity, promotion);

        //then
        assertThat(stockQuantity.getStockQuantity()).isEqualTo(quantity);
    }

    @Test
    public void 유효하지_않은_프로모션_상품일_경우_일반_재고가_증가한다() {
        //given
        int quantity = 10;
        String promotion = "1+100";

        //when
        StockQuantity stockQuantity = new StockQuantity(quantity, promotion);

        //then
        assertThat(stockQuantity.getStockQuantity()).isEqualTo(quantity);
    }

}