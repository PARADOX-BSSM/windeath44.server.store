package paradox.store.domain.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import paradox.store.domain.product.model.Product;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "item",
    indexes = {
        @Index(name = "idx_item_inventory", columnList = "inventory_id"),
        @Index(name = "idx_item_product", columnList = "product_id")
    }
)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_product"))
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "inventory_id",
        nullable = false,
        foreignKey = @ForeignKey(
            name = "fk_item_inventory",
            foreignKeyDefinition = "FOREIGN KEY (inventory_id) REFERENCES inventory(inventory_id) ON DELETE CASCADE"
        )
    )
    private Inventory inventory;

    @Column(name = "acquired_at", nullable = false)
    private LocalDateTime acquiredAt;

    @Column(name = "amount", nullable = false, columnDefinition = "BIGINT DEFAULT 1")
    private Long amount;


    public void increaseAmount(Long quantity) {
        this.amount += quantity;
    }
}
