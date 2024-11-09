//package store.view;
//
//import java.util.List;
//import store.domain.Product;
//
//public class OutputView {
//
//    public void printWelcomeMessage() {
//        System.out.println("안녕하세요. W편의점입니다.");
//    }
//
//    //todo 프로모션이 null일 경우 출력이 안되도록 수정
//    public void printProducts(List<Product> products) {
//        System.out.println("현재 보유하고 있는 상품입니다.\n");
//        for (Product product : products) {
//            System.out.println(
//                    "- " + product.getName() + " " + product.getPrice() + "원 " + product.getStockQuantity() + "개 "
//                            + null
//            );
//        }
//    }
//
//}
