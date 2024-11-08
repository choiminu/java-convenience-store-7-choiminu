package store.view;

import java.util.List;
import store.domain.product.Product;

public class OutputView {

    //todo 프로모션이 null일 경우 출력이 안되도록 수정
    public void printProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(
                    "- " + product.getName() + " " + product.getPrice() + "원 " + product.getStockQuantity() + "개 "
                            + product.getPromotion());
        }
    }

}
