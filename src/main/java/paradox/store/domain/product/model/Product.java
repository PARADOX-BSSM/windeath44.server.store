package paradox.store.domain.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @Column(name = "description", length = 500)
  private String description;

  @Column(name = "sumnail_url", columnDefinition = "TEXT")
  private String sumnailUrl;

  @Column(name = "is_stackable", nullable = false)
  private Boolean isStackable;

  public void update(String name, String description, String sumnailUrl, Boolean isStackable) {
    this.name = name;
    this.description = description;
    this.sumnailUrl = sumnailUrl;
    this.isStackable = isStackable;
  }
}
