package store.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.domain.Order;
import store.domain.OrderProduct;
import store.domain.Product;
import store.domain.Promotion;
import store.utils.parsor.ProductParser;

public class OrderService {

    ProductService productService = new ProductService();
    PromotionService promotionService = new PromotionService();
    ProductParser productParser = new ProductParser();

    // 먼저 사용자의 입력을 변환한다.
    public Map<String, Integer> getShoppingCart(String userInput) {
        return productParser.parseProductQuantities(userInput);
    }

    // 사용자가 놓친 물건이 있는지 확인.
    public Map<String, Integer> getMissingItemsForPromotion(Map<String, Integer> shoppingCart) {
        Map<String, Integer> missingProduct = new LinkedHashMap<>();
        for (String name : shoppingCart.keySet()) {
            Promotion promotion = productService.findPromotionByProductName(promotionService.findAll(), name);
            if (promotion != null) {
                missingProduct.put(name, promotion.getMissingQuantityForPromotion(shoppingCart.get(name)));
            }
        }
        return missingProduct;
    }

    // 주문 수량 중 프로모션 미적용 수량을 계산
    public int calculateNonPromotableQuantity(List<Product> products, int orderQuantity) {
        int totalNonPromotableQuantity = 0;

        Product productss = productService.findPromotionProductFromList(products);
        Promotion promotion = productService.findPromotionByProductName(promotionService.findAll(),
                productss.getName());

        if (promotion != null && productss.getStock() < orderQuantity) {
            totalNonPromotableQuantity = promotion.getNonPromotableQuantity(orderQuantity, productss.getStock());
        }

        return totalNonPromotableQuantity;
    }

    public int calculateDiscount(List<Product> products, int orderQuantity) {
        int totalNonPromotableQuantity = 0;
        for (Product product : products) {
            Promotion promotion = productService.findPromotionByProductName(promotionService.findAll(),
                    product.getName());
            if (promotion != null) {
                totalNonPromotableQuantity += promotion.getNonPromotableQuantity(orderQuantity, product.getStock());
            }
        }
        return totalNonPromotableQuantity;
    }

    public Order createOrder(Map<String, Integer> shoppingCart, Map<Product, Integer> disCountMap) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (String itemName : shoppingCart.keySet()) {
            int orderQuantity = shoppingCart.get(itemName);
            List<Product> findProduct = productService.findProductByName(itemName);
            orderProducts.add(OrderProduct.createOrderProduct(findProduct, orderQuantity));
        }
        return Order.createOrder(orderProducts, disCountMap);
    }

    public void cancel(Order order) {
        order.cancel();
    }
}
