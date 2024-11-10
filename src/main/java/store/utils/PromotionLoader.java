package store.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import store.domain.Promotion;

public class PromotionLoader {
    private final static String DELIMITER = ",";
    private final static int PROMOTION_NAME_INDEX = 0;
    private final static int PROMOTION_REQUIRED_QUANTITY_INDEX = 1;
    private final static int PROMOTION_BONUS_QUANTITY_INDEX = 2;
    private final static int PROMOTION_START_DATE_INDEX = 3;
    private final static int PROMOTION_END_DATE_INDEX = 4;

    public static List<Promotion> loadPromotionsFromFile(String filePath) {
        List<Promotion> promotions = new ArrayList<>();
        List<String> fileLines = FileLoader.loadFile(filePath);

        for (String fileLine : fileLines) {
            parsePromotion(promotions, fileLine);
        }

        return promotions;
    }

    private static void parsePromotion(List<Promotion> promotions, String fileLine) {
        String[] parts = fileLine.split(DELIMITER);
        promotions.add(new Promotion(parts[PROMOTION_NAME_INDEX],
                Integer.parseInt(parts[PROMOTION_REQUIRED_QUANTITY_INDEX]),
                Integer.parseInt(parts[PROMOTION_BONUS_QUANTITY_INDEX]),
                LocalDate.parse(parts[PROMOTION_START_DATE_INDEX]),
                LocalDate.parse(parts[PROMOTION_END_DATE_INDEX])
        ));
    }
}
