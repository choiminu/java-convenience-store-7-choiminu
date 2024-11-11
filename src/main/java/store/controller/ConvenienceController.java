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
            try {
                displayOwnedProducts();
                String input = inputView.readItem();
                Map<String, Integer> shoppingCart = getShoppingCart(input);

                try {
                    productService.checkQuantity(shoppingCart);
                }catch (IllegalArgumentException e) {
                    outputView.printErrorMessage(e.getMessage());
                    input = inputView.readItem();
                    shoppingCart = getShoppingCart(input);
                }


                Map<String, Integer> missingItems = getMissingItems(shoppingCart);


                handleMissingItems(missingItems, shoppingCart);
                handleNonPromotableItems(shoppingCart);


                Order order = null;
                try {
                    order = createOrder(shoppingCart);
                } catch (IllegalArgumentException e) {
                    outputView.printErrorMessage(e.getMessage());
                    input = inputView.readItem();
                    shoppingCart = getShoppingCart(input);
                    missingItems = getMissingItems(shoppingCart);
                }
                applyMembershipDiscount(order);  // 멤버십 할인 적용
                outputView.printOrderSummary(order);
            } catch (Exception e) {
                outputView.printErrorMessage("입력 중 오류가 발생했습니다. 다시 시도해주세요.");
            }
        } while (askForMorePurchases());
    }

    private void displayOwnedProducts() {
        outputView.printOwnedProducts(productService.findAll());
    }

    private Map<String, Integer> getShoppingCart(String input) {
        try {
            Map<String, Integer> shoppingCart = orderService.getShoppingCart(input);
            for (String s : shoppingCart.keySet()) {
                productService.checkIfProductIsAvailable(s);
            }

            return shoppingCart;
        } catch (Exception e) {
            outputView.printErrorMessage("존재하지 않는 상품입니다. 다시 입력해 주세요.");
            return getShoppingCart(inputView.readItem()); // 재입력 받기
        }
    }

    private Map<String, Integer> getMissingItems(Map<String, Integer> shoppingCart) {
        try {
            return orderService.getMissingItemsForPromotion(shoppingCart);
        } catch (Exception e) {
            outputView.printErrorMessage("잘못된 입력입니다. 다시 입력해 주세요.");
            return getMissingItems(shoppingCart);
        }
    }

    private void handleMissingItems(Map<String, Integer> missingItems, Map<String, Integer> shoppingCart) {
        missingItems.forEach((item, quantity) -> handleMissingItem(item, quantity, shoppingCart));
    }

    private void handleMissingItem(String item, int quantity, Map<String, Integer> shoppingCart) {
        String answer = inputView.readMissingProduct(item, quantity);
        while (!"Y".equalsIgnoreCase(answer) && !"N".equalsIgnoreCase(answer)) {
            outputView.printErrorMessage("잘못된 입력입니다. 다시 입력해 주세요.");
            answer = inputView.readMissingProduct(item, quantity);
        }
        if ("Y".equalsIgnoreCase(answer)) {
            shoppingCart.put(item, shoppingCart.get(item) + quantity);
        }
    }

    private void handleNonPromotableItems(Map<String, Integer> shoppingCart) {
        shoppingCart.forEach((name, order) -> handleNonPromotableItem(name, order, shoppingCart));
    }

    private void handleNonPromotableItem(String name, int order, Map<String, Integer> shoppingCart) {
        try {
            List<Product> productByName = productService.findProductByName(name);
            int nonPromotableQuantity = orderService.calculateNonPromotableQuantity(productByName, order);
            if (nonPromotableQuantity > 0) {
                confirmNonPromotableItem(name, nonPromotableQuantity, shoppingCart);
            }
        } catch (Exception e) {
            outputView.printErrorMessage("잘못된 입력입니다. 다시 입력해 주세요.");
            handleNonPromotableItem(name, order, shoppingCart); // 재입력 받기
        }
    }

    private void confirmNonPromotableItem(String name, int nonPromotableQuantity, Map<String, Integer> shoppingCart) {
        String answer = inputView.readNonPromotableConfirmation(name, nonPromotableQuantity);
        while (!"Y".equalsIgnoreCase(answer) && !"N".equalsIgnoreCase(answer)) {
            outputView.printErrorMessage("잘못된 입력입니다. 다시 입력해 주세요.");
            answer = inputView.readNonPromotableConfirmation(name, nonPromotableQuantity);
        }
        if ("N".equals(answer)) {
            shoppingCart.put(name, shoppingCart.get(name) - nonPromotableQuantity);
        }
    }

    private Order createOrder(Map<String, Integer> shoppingCart) {
        Map<Product, Integer> discountList = applyDiscounts(shoppingCart);
        return orderService.createOrder(shoppingCart, discountList);
    }

    private void applyMembershipDiscount(Order order) {
        String membershipInput = inputView.requestMemberShip();
        while (!"Y".equalsIgnoreCase(membershipInput) && !"N".equalsIgnoreCase(membershipInput)) {
            outputView.printErrorMessage("잘못된 입력입니다. 다시 입력해 주세요.");
            membershipInput = inputView.requestMemberShip();
        }
        if ("Y".equalsIgnoreCase(membershipInput)) {
            order.applyMembershipDiscount("true");
        } else {
            order.applyMembershipDiscount("false");
        }
    }

    private Map<Product, Integer> applyDiscounts(Map<String, Integer> shoppingCart) {
        Map<Product, Integer> discountList = new LinkedHashMap<>();
        shoppingCart.forEach((itemName, quantity) -> addDiscount(itemName, quantity, discountList));
        return discountList;
    }

    private void addDiscount(String itemName, int quantity, Map<Product, Integer> discountList) {
        try {
            Product product = productService.findPromotionProductByName(itemName);
            Product generalProductByName = productService.findGeneralProductByName(itemName);
            Promotion promotion = promotionService.findByName(product.getPromotionName());

            if (promotion != null && promotion.isDateValid()) {  // 프로모션이 유효한 경우만 추가
                int discountQuantity = calculateDiscountQuantity(promotion, quantity, product);
                    discountList.put(product, discountQuantity);
            }
        } catch (Exception e) {
            outputView.printErrorMessage("잘못된 입력입니다. 다시 입력해 주세요.");
            addDiscount(itemName, quantity, discountList); // 재입력 받기
        }
    }

    private int calculateDiscountQuantity(Promotion promotion, int quantity, Product product) {
        int promotionBonusQuantity = promotion.getPromotionBonusQuantity(quantity);
        int exceededBonusQuantity = promotion.getExcepedBonusQuantity(product.getStock());
        return Math.min(promotionBonusQuantity, exceededBonusQuantity);
    }

    // 구매 추가 여부 묻는 메서드
    private boolean askForMorePurchases() {
        String answer = inputView.requestMoreBuy();
        while (!"Y".equalsIgnoreCase(answer) && !"N".equalsIgnoreCase(answer)) {
            outputView.printErrorMessage("잘못된 입력입니다. 다시 입력해 주세요.");
            answer = inputView.requestMoreBuy();
        }
        return "Y".equalsIgnoreCase(answer);
    }
}
