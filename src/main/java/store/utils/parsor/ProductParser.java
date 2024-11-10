package store.utils.parsor;

import java.util.HashMap;
import java.util.Map;

public class ProductParser {

    public Map<String, Integer> parseProductQuantities(String input) {
        Map<String, Integer> map = new HashMap<>();
        for (String item : input.split(",")) {
            item = cleanInput(item);
            if (!isValidFormat(item)) {
                throw new IllegalArgumentException("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
            }
            addToMap(map, item);
        }
        return map;
    }

    private String cleanInput(String item) {
        if (!item.matches(".*\\[.*\\].*") || item.contains("[[") || item.contains("]]")) {
            throw new IllegalArgumentException("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
        return item.replaceAll("[\\[\\]]", "").trim();
    }

    private boolean isValidFormat(String item) {
        return item.contains("-") && item.split("-").length == 2;
    }

    private void addToMap(Map<String, Integer> map, String item) {
        String[] keyValue = item.split("-");
        int quantity = Integer.parseInt(keyValue[1].trim());
        map.put(keyValue[0].trim(), quantity);
    }
}