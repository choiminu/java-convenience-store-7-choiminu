package store.utils;

import java.util.ArrayList;
import java.util.List;
import store.domain.Item;
import store.domain.Promotion;

public class FileLoader {

    public static List<Item> itemLoad(String path) {
        List<Item> itemList = new ArrayList<>();
        List<String> lines = FileReadHelper.readLines(path);

        for (String line : lines) {
            parseItem(itemList, line);
        }

        return itemList;
    }

    public static List<Promotion> promotionLoad(String path) {
        List<Promotion> promotionList = new ArrayList<>();
        List<String> lines = FileReadHelper.readLines(path);

        for (String line : lines) {
            parsePromotion(promotionList, line);
        }
        return promotionList;
    }

    private static void parseItem(List<Item> items, String line) {
        String[] split = line.split(",");
        String name = split[0];
        int price = Integer.parseInt(split[1]);
        int quantity = Integer.parseInt(split[2]);
        String promotion = split[3];
        Item item = new Item(name, price, quantity, promotion);
        items.add(item);
    }

    private static void parsePromotion(List<Promotion> promotions, String line) {
        String[] fields = line.split(",");
        String name = fields[0];
        int requiredQuantity = Integer.parseInt(fields[1]);
        int freeQuantity = Integer.parseInt(fields[2]);
        String startDate = fields[3];
        String endDate = fields[4];
        Promotion promotion = Promotion.findPromotion(name, requiredQuantity, freeQuantity, startDate, endDate);
        promotions.add(promotion);
    }
}
