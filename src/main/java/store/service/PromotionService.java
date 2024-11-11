package store.service;

import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.PromotionType;
import store.utils.loader.PromotionLoader;

public class PromotionService {

    private final String filePath = "src/main/resources/promotions.md";

    public List<Promotion> findAll() {
        return PromotionLoader.loadPromotionsFromFile(filePath);
    }

    public int discount(Product product, PromotionType promotionType) {
        return promotionType.calculateBonusQuantity(product.getStock());
    }

    public Promotion findByName(String name) {
        return findAll().stream()
                .filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
    }
}
