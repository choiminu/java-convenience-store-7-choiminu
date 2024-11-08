package store.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.domain.product.Product;
import store.repository.loader.ProductLoader;
import store.utils.FileReadHelper;
import store.utils.ProductParser;

class ProductRepositoryTest {

    private final FileReadHelper fileReadHelper = new FileReadHelper();
    private final ProductParser productParser = new ProductParser();
    private final ProductLoader productLoader = new ProductLoader(productParser, fileReadHelper);
    private final ProductRepository productRepository = new ProductRepository(productLoader);


    @ParameterizedTest
    @CsvSource({
            "0, 콜라, 1000, 10, 탄산2+1",
            "1, 콜라, 1000, 10, null",
            "2, 사이다, 1000, 8, 탄산2+1",
            "3, 사이다, 1000, 7, null",
            "4, 오렌지주스, 1800, 9, MD추천상품",
            "5, 탄산수, 1200, 5, 탄산2+1",
            "6, 물, 500, 10, null",
            "7, 비타민워터, 1500, 6, null",
            "8, 감자칩, 1500, 5, 반짝할인",
            "9, 감자칩, 1500, 5, null",
            "10, 초코바, 1200, 5, MD추천상품",
            "11, 초코바, 1200, 5, null",
            "12, 에너지바, 2000, 5, null",
            "13, 정식도시락, 6400, 8, null",
            "14, 컵라면, 1700, 1, MD추천상품",
            "15, 컵라면, 1700, 10, null"
    })
    public void 상품재고_모두_조회(int index, String itemName, int price, int stockQuantity, String promotion) {
        //given
        List<Product> productList = productRepository.findAll();
        if (promotion.equals("null")) {
            promotion = null;
        }

        //when then
        assertThat(productList.get(index).getId()).isEqualTo(index);
        assertThat(productList.get(index).getName()).isEqualTo(itemName);
        assertThat(productList.get(index).getPrice()).isEqualTo(price);
        assertThat(productList.get(index).getStockQuantity()).isEqualTo(stockQuantity);
        assertThat(productList.get(index).getPromotion()).isEqualTo(promotion);
    }

    @Test
    public void 상품재고_단건_조회() {
        //given
        Long productId = 1L;
        String productName = "콜라";
        int productPrice = 1000;
        int productQuantity = 10;
        String productPromotion = null;

        //when
        Product findProduct = productRepository.findById(1L);

        //then
        assertThat(findProduct.getId()).isEqualTo(productId);
        assertThat(findProduct.getName()).isEqualTo(productName);
        assertThat(findProduct.getPrice()).isEqualTo(productPrice);
        assertThat(findProduct.getStockQuantity()).isEqualTo(productQuantity);
        assertThat(findProduct.getPromotion()).isEqualTo(productPromotion);
    }

    @Test
    public void 상품_이름으로_조회() {
        //given
        Long productId = 0L;
        String productName = "콜라";
        int productPrice = 1000;
        int productQuantity = 10;
        String productPromotion = "탄산2+1";

        //when
        Product findProduct = productRepository.findByName(productName);

        //then
        assertThat(findProduct.getId()).isEqualTo(productId);
        assertThat(findProduct.getName()).isEqualTo(productName);
        assertThat(findProduct.getPrice()).isEqualTo(productPrice);
        assertThat(findProduct.getStockQuantity()).isEqualTo(productQuantity);
        assertThat(findProduct.getPromotion()).isEqualTo(productPromotion);
    }

    @Test
    public void 상품_이름으로_조회_예외() {
        //given
        String productName = "없는상품";

        //then when
        Assertions.assertThatThrownBy(() -> productRepository.findByName(productName))
                .isInstanceOf(IllegalArgumentException.class);

    }
}