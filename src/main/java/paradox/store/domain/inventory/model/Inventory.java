package paradox.store.domain.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import paradox.store.domain.inventory.model.vo.ItemList;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", nullable = false)
    private Long inventoryId;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @OneToMany(mappedBy = "inventory")
    @BatchSize(size = 100)
    @Builder.Default
    private List<Item> items = new ArrayList<>();

    public ItemList getItemList() {
        return new ItemList(items);
    }
}
