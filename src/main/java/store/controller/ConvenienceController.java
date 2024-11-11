package store.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.domain.Order;
import store.domain.Product;
import store.domain.Promotion;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ProductService productService = new ProductService();
    private final PromotionService promotionService = new PromotionService();
    private final OrderService orderService = new OrderService();

    public void run() {
        do {
            outputView.printOwnedProducts(productService.findAll());

            String input = inputView.readItem();

            Map<String, Integer> shoppingCart = orderService.getShoppingCart(input);

            Map<String, Integer> missingItems = orderService.getMissingItemsForPromotion(shoppingCart);

            handleMissingItems(missingItems, shoppingCart);

            handleNonPromotableItems(shoppingCart);

            Order order = orderService.createOrder(shoppingCart, applyDiscounts(shoppingCart));

            order.applyMembershipDiscount(inputView.requestMemberShip());

            outputView.printOrderSummary(order);

        } while (inputView.requestMoreBuy().equals("Y"));
    }

    // 부족한 수량을 장바구니에 추가할지 확인
    private void handleMissingItems(Map<String, Integer> missingItems, Map<String, Integer> shoppingCart) {
        for (String item : missingItems.keySet()) {
            String answer = inputView.readMissingProduct(item, missingItems.get(item));
            if (answer.equalsIgnoreCase("Y")) {
                shoppingCart.put(item, shoppingCart.get(item) + missingItems.get(item));
            }
        }
    }

    private void handleNonPromotableItems(Map<String, Integer> shoppingCart) {
        for (String name : shoppingCart.keySet()) {
            List<Product> productByName = productService.findProductByName(name);
            Integer order = shoppingCart.get(name);
            int nonPromotableQuantity = orderService.calculateNonPromotableQuantity(productByName, order);

            if (nonPromotableQuantity > 0) {
                String answer = inputView.readNonPromotableConfirmation(name, nonPromotableQuantity);
                if (answer.equals("N")) {
                    shoppingCart.put(name, shoppingCart.get(name) - nonPromotableQuantity);
                }
                return;
            }
        }
    }

    // 장바구니에 할인을 적용합니다.
    private Map<Product, Integer> applyDiscounts(Map<String, Integer> shoppingCart) {
        Map<Product, Integer> discountList = new LinkedHashMap<>();

        shoppingCart.forEach((itemName, quantity) -> {
            Product product = productService.findPromotionProductByName(itemName);
            List<Product> productByName = productService.findProductByName(itemName);
            Product promotionProductFromList = productService.findPromotionProductFromList(productByName);
            Promotion promotion = promotionService.findByName(product.getPromotionName());

            if (promotion != null) {
                int result = 0;
                // 예상 수량
                int promotionBonusQuantity = promotion.getPromotionBonusQuantity(quantity);

                // 실제 받을 수 있는거
                int excepedBonusQuantity = promotion.getExcepedBonusQuantity(product.getStock());

                if (promotionBonusQuantity <= excepedBonusQuantity) {
                    result = promotionBonusQuantity;
                    discountList.put(product, result);
                    return;
                }

                discountList.put(product, excepedBonusQuantity);
            }
        });

        return discountList;
    }

}
