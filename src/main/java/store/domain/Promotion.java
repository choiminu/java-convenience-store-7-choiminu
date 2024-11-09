package store.domain;

import java.time.LocalDate;

public class Promotion {
    private final String name;
    private final int requiredQuantity;
    private final int freeQuantity;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int requiredQuantity, int freeQuantity, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.requiredQuantity = requiredQuantity;
        this.freeQuantity = freeQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion createPromotion(String name, int requiredQuantity, int freeQuantity,
                                            String startDate,
                                            String endDate) {
        return new Promotion(name, requiredQuantity, freeQuantity, LocalDate.parse(startDate),
                LocalDate.parse(endDate));
    }

    public String getName() {
        return name;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }

    public String getStartDate() {
        return String.valueOf(startDate);
    }

    public String getEndDate() {
        return String.valueOf(endDate);
    }
}
