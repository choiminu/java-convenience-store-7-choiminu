package store.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.domain.Item;
import store.utils.ItemLoader;

public class ItemRepository {
    private final static Map<String, List<Item>> items = new LinkedHashMap<>();

    public ItemRepository(ItemLoader itemLoader) {
        init(itemLoader);
    }

    private void init(ItemLoader itemLoader) {
        List<Item> itemList = itemLoader.loadItems();
        for (Item item : itemList) {
            items.computeIfAbsent(item.getItemName(), k -> new ArrayList<>()).add(item);
        }
    }

    public List<Item> findAll() {
        Collection<List<Item>> values = items.values();
        List<Item> itemList = new ArrayList<>();
        for (List<Item> value : values) {
            itemList.addAll(value);
        }
        return itemList;
    }

    public List<Item> findByItemName(String name) {
        List<Item> itemList = items.get(name);
        if (itemList == null || itemList.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }
        return itemList;
    }
}

