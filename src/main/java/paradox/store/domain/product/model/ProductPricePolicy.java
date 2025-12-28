package paradox.store.domain.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.*;
import paradox.store.domain.product.model.key.ProductPricePolicyId;

@Entity
@Table(name = "product_price_policy")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ProductPricePolicy {

  @EmbeddedId
  private ProductPricePolicyId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("productId")
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("currencyId")
  @JoinColumn(name = "currency_id", nullable = false)
  private Currency currency;

  @Column(name = "price_amount", nullable = false)
  private Long priceAmount;

  public void updatePriceAmount(Long priceAmount) {
    this.priceAmount = priceAmount;
  }

    public Long getProductId() {
      return this.id.getProductId();
    }

    public Long getCurrencyId() {
      return this.id.getCurrencyId();
    }
}
