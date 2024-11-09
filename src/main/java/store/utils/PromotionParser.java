package store.utils;

import java.time.LocalDate;
import store.domain.Promotion;

public class PromotionParser {
    private static final String DELIMITER = ",";

    //탄산2+1,2,1,2024-01-01,2024-12-31
    public Promotion parsePromotion(String line) {
        String[] fields = line.split(DELIMITER);

        String name = fields[0];
        int requiredQuantity = Integer.parseInt(fields[1]);
        int freeQuantity = Integer.parseInt(fields[2]);
        String startDate = String.valueOf(LocalDate.parse(fields[3]));
        String endDate = String.valueOf(LocalDate.parse(fields[4]));

        return Promotion.createPromotion(name, requiredQuantity, freeQuantity, startDate, endDate);
    }
}
