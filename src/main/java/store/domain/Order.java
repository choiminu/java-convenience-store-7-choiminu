package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderProduct> orderProductList;

    public Order(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public static Order createOrder(OrderProduct... orderProducts) {
        List<OrderProduct> orderProductList = new ArrayList<>();

        for (OrderProduct orderProduct : orderProducts) {
            orderProductList.add(orderProduct);
        }

        return new Order(orderProductList);
    }

    public void cancel() {
        for (OrderProduct orderProduct : orderProductList) {
            orderProduct.cancel();
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderProduct orderProduct : orderProductList) {
            totalPrice += orderProduct.getOrderPrice();
        }
        return totalPrice;
    }
}
