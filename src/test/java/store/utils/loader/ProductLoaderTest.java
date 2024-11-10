package store.utils.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import store.domain.Product;

class ProductLoaderTest {

    String filePath = "src/main/resources/products.md";
    List<Product> productList = ProductLoader.loadProductsFromFile(filePath);

    @Test
    public void 파일에서_상품_목록을_읽어_상품_리스트로반환한다() {
        assertEquals(productList.size(), 16);
    }

    @Test
    public void 첫_번째_상품_검증() {
        assertEquals(productList.get(0).getName(), "콜라");
        assertEquals(productList.get(0).getPrice(), 1000);
        assertEquals(productList.get(0).getStock(), 10);
        assertEquals(productList.get(0).getPromotionName(), "탄산2+1");
    }

    @Test
    public void 두_번째_상품_검증() {
        assertEquals(productList.get(1).getName(), "콜라");
        assertEquals(productList.get(1).getPrice(), 1000);
        assertEquals(productList.get(1).getStock(), 10);
        assertEquals(productList.get(1).getPromotionName(), "null");
    }

    @Test
    public void 세_번째_상품_검증() {
        assertEquals(productList.get(2).getName(), "사이다");
        assertEquals(productList.get(2).getPrice(), 1000);
        assertEquals(productList.get(2).getStock(), 8);
        assertEquals(productList.get(2).getPromotionName(), "탄산2+1");
    }

}