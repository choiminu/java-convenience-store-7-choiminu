package store.utils;

import java.util.List;
import store.domain.Promotion;

public class PromotionLoader {
    private final String filePath;

    public PromotionLoader(String filePath) {
        this.filePath = filePath;
    }

    public List<Promotion> loadPromotion() {
        return FileLoader.promotionLoad(filePath);
    }
}
