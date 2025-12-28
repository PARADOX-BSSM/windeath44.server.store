package paradox.store.domain.inventory.model.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import paradox.store.domain.inventory.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemList {

    private List<Item> items = new ArrayList<>();

    public ItemList(List<Item> items) {
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Optional<Item> findByProductId(Long productId) {
        return items.stream()
                .filter(item -> item.getProduct().equals(productId))
                .findFirst();
    }

    public long getTotalAmount() {
        return items.stream()
                .mapToLong(Item::getAmount)
                .sum();
    }

    public boolean containsProduct(Long productId) {
        return items.stream()
                .anyMatch(item -> item.getProduct().equals(productId));
    }

    public void add(Item item) {
        this.items.add(item);
    }

    public void remove(Item item) {
        this.items.remove(item);
    }
}
