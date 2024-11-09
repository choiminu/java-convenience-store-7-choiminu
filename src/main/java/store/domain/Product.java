//package store.domain;
//
//public class Product {
//    private Long id;
//    private String name;
//    private int price;
//    private StockQuantity stockQuantity;
//    private Promotion promotion;
//
//    public Product(String name, int price, StockQuantity stockQuantity, Promotion promotion) {
//        this.name = name;
//        this.price = price;
//        this.stockQuantity = stockQuantity;
//        this.promotion = promotion;
//    }
//
//    public static Product createProduct(String name, int price, int quantity, Promotion promotion) {
//        return new Product(name, price, new StockQuantity(quantity, promotion), promotion);
//    }
//
//    public void addStock(int quantity) {
////        stockQuantity.increaseQuantity(quantity);
//    }
//
//    public void removeStock(int quantity) {
//        int calculatedQuantity = this.stockQuantity.getStockQuantity() - quantity;
//
//        if (calculatedQuantity < 0) {
//            throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
//        }
//
////        this.stockQuantity.reductionQuantity(quantity);
//    }
//
//    public int applyPromotion(int quantity) {
//        if (isPromotionAvailable()) {
//            int promoQuantity = promotion.calculateFreeItems(quantity, stockQuantity.getStockQuantity());
////            stockQuantity.reductionQuantity(promoQuantity);
//            return promoQuantity;
//        }
//        return 0;
//    }
//
//    public boolean isPromotionAvailable() {
//        return promotion != null && stockQuantity.getStockQuantity() > 0;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getStockQuantity() {
//        return stockQuantity.getStockQuantity();
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public Promotion getPromotion() {
//        return promotion;
//    }
//}
