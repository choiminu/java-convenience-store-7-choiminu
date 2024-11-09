package store.utils;

import java.util.List;
import store.domain.Item;

public class ItemLoader {
    private final String filePath;

    public ItemLoader(String filePath) {
        this.filePath = filePath;
    }

    public List<Item> loadItems() {
        return FileLoader.itemLoad(filePath);
    }
}
