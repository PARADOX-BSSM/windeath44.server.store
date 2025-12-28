package paradox.store.domain.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "currency")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "currency_id", nullable = false)
  private Long currencyId;

  @Column(name = "code", nullable = false, length = 50)
  private String code;

  public static Currency of(String code) {
      return new Currency(code);
  }

  private Currency(String code) {
      this.code = code;
  }

  public void updateCode(String code) {
    this.code = code;
  }
}
