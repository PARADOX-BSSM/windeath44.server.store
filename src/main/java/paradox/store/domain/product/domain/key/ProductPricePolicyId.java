package paradox.store.domain.product.domain.key;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ProductPricePolicyId implements Serializable {

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "currency_id", nullable = false)
  private Long currencyId;

  public static ProductPricePolicyId create(Long productId, Long currencyId) {
      return new ProductPricePolicyId(productId, currencyId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductPricePolicyId that = (ProductPricePolicyId) o;
    return Objects.equals(productId, that.productId) && Objects.equals(currencyId, that.currencyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, currencyId);
  }
}
