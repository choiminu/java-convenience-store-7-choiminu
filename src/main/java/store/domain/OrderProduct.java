package store.domain;

public class OrderProduct {
    private Product product;
    private int orderPrice;
    private int count;

    public OrderProduct(Product product, int orderPrice, int count) {
        this.product = product;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderProduct createOrderProduct(Product product, int orderCount) {
        product.removeStock(orderCount);
        return new OrderProduct(product, product.getPrice(), orderCount);
    }

    public Product getProduct() {
        return product;
    }

    public void cancel() {
        this.product.addStock(count);
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }
}
