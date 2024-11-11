package store.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import store.domain.Order;
import store.domain.OrderProduct;
import store.domain.Product;

public class OutputView {
    public void printOwnedProducts(List<Product> products) {
        System.out.println("안녕하세요. W편의점입니다.");
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

        String quantity = "재고 없음 ";
        if (product.getStock() == 0) {
            return "- " + product.getName() + " " + formattedPrice + "원 "
                    + quantity + formattedPromotion;
        }

        return "- " + product.getName() + " " + formattedPrice + "원 "
                + product.getStock() + "개 " + formattedPromotion;
    }


    public void printOrderSummary(Order order) {
        NumberFormat currencyFormat = NumberFormat.getInstance(Locale.KOREA);

        List<OrderProduct> orderProducts = order.getOrderProducts();

        // 헤더 출력
        System.out.println("\n============== W 편의점 ==============");
        System.out.printf("%-17s %6s %10s\n", "상품명", "수량", "금액");

        // 상품 정보 출력
        int totalAmount = 0;
        for (OrderProduct orderProduct : orderProducts) {
            String productName = orderProduct.getProductName();
            int quantity = orderProduct.getOrderQuantity();
            int price = orderProduct.getOrderPrice() * orderProduct.getOrderQuantity();

            if (quantity <= 0) {
                System.out.printf("%-17s %6s %10s\n", productName, "재고 없음", currencyFormat.format(price));
            } else {
                System.out.printf("%-17s %6s %10s\n", productName, quantity, currencyFormat.format(price));
            }

            // 수량이 있는 상품 출력
            totalAmount += price;
        }

        // 증정품 정보 출력
        System.out.println("==============증정================");
        for (Product freeProduct : order.getFreeItems().keySet()) {
            int bonusQuantity = order.getFreeItems().get(freeProduct);
            if (bonusQuantity > 0) {
                // 증정품은 수량만 출력
                System.out.printf("%-17s %6d\n", freeProduct.getName(), bonusQuantity);
            }
        }

        // 할인 및 최종 금액 정보 출력
        System.out.println("====================================");
        System.out.printf("%-12s%8d%10s\n", "총구매액", order.getOrderCount(), currencyFormat.format(totalAmount));
        System.out.printf("%-12s%18s\n", "행사할인", currencyFormat.format(-order.getDiscountAmount()));
        System.out.printf("%-12s%18s\n", "멤버십할인", currencyFormat.format(-order.getMembershipDiscountAmount()));
        System.out.printf("%-12s%18s\n", "내실돈", currencyFormat.format(order.getFinalPriceWithMembership()));
    }
}
