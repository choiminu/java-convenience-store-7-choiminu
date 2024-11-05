package store.repository.loader;

import static org.assertj.core.api.Assertions.tuple;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import store.domain.product.Product;
import store.utils.FileReadHelper;
import store.utils.ProductParser;

class ProductLoaderTest {
    private static final String FILE_PATH = "src/main/resources/products.md";

    @Test
    public void 상품_정보_가져오기() throws IOException {
        //given
        ProductLoader productLoader = new ProductLoader(new ProductParser(), new FileReadHelper());

        //when
        List<Product> products = productLoader.load(FILE_PATH);

        //then
        org.assertj.core.api.Assertions.assertThat(products)
                .extracting(Product::getName, Product::getPrice, Product::getStockQuantity, Product::getPromotion)
                .containsExactly(
                        tuple("콜라", 1000, 10, "탄산2+1"),
                        tuple("콜라", 1000, 10, null),
                        tuple("사이다", 1000, 8, "탄산2+1"),
                        tuple("사이다", 1000, 7, null),
                        tuple("오렌지주스", 1800, 9, "MD추천상품"),
                        tuple("탄산수", 1200, 5, "탄산2+1"),
                        tuple("물", 500, 10, null),
                        tuple("비타민워터", 1500, 6, null),
                        tuple("감자칩", 1500, 5, "반짝할인"),
                        tuple("감자칩", 1500, 5, null),
                        tuple("초코바", 1200, 5, "MD추천상품"),
                        tuple("초코바", 1200, 5, null),
                        tuple("에너지바", 2000, 5, null),
                        tuple("정식도시락", 6400, 8, null),
                        tuple("컵라면", 1700, 1, "MD추천상품"),
                        tuple("컵라면", 1700, 10, null)
                );
    }
}