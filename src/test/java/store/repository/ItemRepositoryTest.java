package store.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import store.domain.Item;
import store.utils.ItemLoader;

class ItemRepositoryTest {

    ItemLoader itemLoader = new ItemLoader("src/main/resources/products.md");
    ItemRepository itemRepository = new ItemRepository(itemLoader);


    @Test
    public void 상품_재고_전체_조회() {
        List<Item> allItems = itemRepository.findAll();

        assertNotNull(allItems);
        assertFalse(allItems.isEmpty(), "상품 목록이 비어 있지 않아야 합니다.");

        Item firstItem = allItems.get(0);
        assertNotNull(firstItem.getItemName(), "첫 번째 상품의 이름이 존재해야 합니다.");
    }

    @Test
    public void 특정_상품명으로_조회() {
        List<Item> colaItems = itemRepository.findByItemName("콜라");

        assertNotNull(colaItems);
        assertFalse(colaItems.isEmpty(), "콜라 상품 목록이 비어 있지 않아야 합니다.");

        for (Item item : colaItems) {
            assertEquals("콜라", item.getItemName(), "상품명이 '콜라'이어야 합니다.");
        }
    }

    @Test
    public void 동일한_이름을_가진_아이템_조회() {
        //given
        String itemName = "콜라";

        //when
        List<Item> items = itemRepository.findByItemName(itemName);

        //then
        Assertions.assertThat(items.size()).isEqualTo(2);
    }

    @Test
    public void 존재하지_않는_상품명으로_조회() {
        //given
        String itemName = "존재하지 않은 상품명";

        //when then
        Assertions.assertThatThrownBy(() -> itemRepository.findByItemName(itemName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}