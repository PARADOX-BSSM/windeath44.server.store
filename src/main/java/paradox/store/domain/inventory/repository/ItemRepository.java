package paradox.store.domain.inventory.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paradox.store.domain.inventory.model.Item;

import java.util.List;
import java.util.Optional;
import paradox.store.domain.inventory.model.Inventory;
import paradox.store.domain.product.model.Product;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i.itemId FROM Item i WHERE i.inventory.inventoryId = :inventoryId ORDER BY i.itemId ASC")
    Slice<Long> findItemIdsByInventoryId(@Param("inventoryId") Long inventoryId, Pageable pageable);

    @Query("SELECT i.itemId FROM Item i WHERE i.inventory.inventoryId = :inventoryId AND i.itemId > :cursor ORDER BY i.itemId ASC")
    Slice<Long> findItemIdsByInventoryIdAndCursor(@Param("inventoryId") Long inventoryId, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT i FROM Item i JOIN FETCH i.product WHERE i.itemId IN :itemIds ORDER BY i.itemId ASC")
    List<Item> findAllByItemIdsWithProduct(@Param("itemIds") List<Long> itemIds);


    Optional<Item> findByInventoryAndProduct(Inventory inventory, Product product);
}
