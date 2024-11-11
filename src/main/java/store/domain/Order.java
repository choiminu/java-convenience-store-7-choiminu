package store.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private List<OrderProduct> orderProductList;
    private Map<Product, Integer> freeItems = new LinkedHashMap<>();
    private static final int MAX_MEMBERSHIP_DISCOUNT = 8000;
    private static final double MEMBERSHIP_DISCOUNT_RATE = 0.3;
    private boolean isMembershipDiscountApplied = false;

    public Order(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public static Order createOrder(List<OrderProduct> orderProducts, Map<Product, Integer> discounts) {
        Order order = new Order(orderProducts);
        order.applyDiscounts(discounts);
        return order;
    }

    private void applyDiscounts(Map<Product, Integer> discounts) {
        this.freeItems.putAll(discounts);
    }

    public int getDiscountAmount() {
        int discountAmount = 0;
        for (Product product : freeItems.keySet()) {
            if (product != null) {
                discountAmount += product.getPrice() * freeItems.get(product);
            }
        }
        return discountAmount;
    }

    public void applyMembershipDiscount(String answer) {
        if (answer.equalsIgnoreCase("Y")) {
            isMembershipDiscountApplied = true;
        }
        if (answer.equals("N")) {
            isMembershipDiscountApplied = false;
        }
    }

    public int getMembershipDiscountAmount() {
        if (!isMembershipDiscountApplied) {
            return 0;
        }
        int result = getTotalPrice();
        for (OrderProduct orderProduct : orderProductList) {
            for (Product product : freeItems.keySet()) {
                if (orderProduct.getProductName().equals(product.getName())) {
                    result -= product.getPrice() * orderProduct.getOrderQuantity();
                }
            }
        }
        int membershipDiscountAmount = (int) (result * MEMBERSHIP_DISCOUNT_RATE);
        membershipDiscountAmount = Math.min(membershipDiscountAmount, MAX_MEMBERSHIP_DISCOUNT);
        return membershipDiscountAmount;
    }

    public int getOrderPrice() {
        int price = 0;
        for (OrderProduct orderProduct : orderProductList) {
            price += orderProduct.getOrderPrice();
        }
        return price;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderProduct orderProduct : orderProductList) {
            totalPrice += orderProduct.getOrderPrice() * orderProduct.getOrderQuantity();
        }
        return totalPrice;
    }

    public int getOrderCount() {
        int count = 0;
        for (OrderProduct orderProduct : orderProductList) {
            count += orderProduct.getOrderQuantity();
        }
        return count;
    }

    public int getFinalPriceWithMembership() {
        return getTotalPrice() - getDiscountAmount() - getMembershipDiscountAmount();
    }

    public void cancel() {
        for (OrderProduct orderProduct : orderProductList) {
            orderProduct.cancel();
        }
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProductList;
    }

    public Map<Product, Integer> getFreeItems() {
        return freeItems;
    }
}
