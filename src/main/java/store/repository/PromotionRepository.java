package store.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.domain.Promotion;
import store.utils.PromotionLoader;

public class PromotionRepository {
    private final Map<String, Promotion> promotions = new LinkedHashMap<>();

    public PromotionRepository(PromotionLoader promotionLoader) {
        init(promotionLoader);
    }

    private void init(PromotionLoader promotionLoader) {
        List<Promotion> promotions = promotionLoader.loadPromotion();
        for (Promotion promotion : promotions) {
            this.promotions.put(promotion.getName(), promotion);
        }
    }

    public List<Promotion> findAll() {
        return new ArrayList<>(promotions.values());
    }

    public Promotion findByName(String name) {
        return promotions.get(name);
    }
}
