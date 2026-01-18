package paradox.store.domain.purchase.model;

import jakarta.persistence.*;
import lombok.*;
import paradox.store.domain.product.model.Product;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "purchase",
    indexes = {
        @Index(name = "idx_purchase_user_id", columnList = "user_id"),
        @Index(name = "idx_purchase_created_at", columnList = "created_at")
    }
)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id", nullable = false)
    private Long purchaseId;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
