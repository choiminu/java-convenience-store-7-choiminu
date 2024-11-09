package store.repository.loader;

import java.util.ArrayList;
import java.util.List;
import store.domain.Promotion;
import store.utils.FileReadHelper;
import store.utils.PromotionParser;

public class PromotionLoader implements Loader {

    private final PromotionParser promotionParser;
    private final FileReadHelper fileReadHelper;

    public PromotionLoader(PromotionParser promotionParser, FileReadHelper fileReadHelper) {
        this.promotionParser = promotionParser;
        this.fileReadHelper = fileReadHelper;
    }

    @Override
    public List<Promotion> load(String path) {
        List<Promotion> promotions = new ArrayList<>();
        List<String> lines = fileReadHelper.readLines(path);

        for (String line : lines) {
            promotions.add(promotionParser.parsePromotion(line));
        }

        return promotions;
    }
}
