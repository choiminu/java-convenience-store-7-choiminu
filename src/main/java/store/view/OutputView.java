package store.view;

import java.text.NumberFormat;
import java.util.List;
import store.domain.Product;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
    }

    public void printOwnedProducts(List<Product> products) {
        System.out.println("현재 보유하고 있는 상품입니다.\n");
        for (Product product : products) {
            printProductDetails(product);
        }
    }

    private void printProductDetails(Product product) {
        String formattedPrice = formatPrice(product.getPrice());
        String formattedPromotion = product.getPromotionName();
        if (product.getPromotionName().equals("null")) {
            formattedPromotion = "";
        }
        String itemDetails = formatItemDetails(product, formattedPrice, formattedPromotion);
        System.out.println(itemDetails);
    }

    private String formatPrice(int price) {
        return NumberFormat.getNumberInstance().format(price);
    }

    private String formatItemDetails(Product product, String formattedPrice, String formattedPromotion) {
        return "- " + product.getName() + " " + formattedPrice + "원 "
                + product.getStock() + "개 " + formattedPromotion;
    }

}
