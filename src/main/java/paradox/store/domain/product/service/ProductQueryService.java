package paradox.store.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paradox.store.domain.product.domain.Product;
import paradox.store.domain.product.dto.ProductResponse;
import paradox.store.domain.product.exception.NotFoundProductException;
import paradox.store.domain.product.repository.ProductRepository;
import paradox.store.global.dto.CursorPage;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

  private final ProductRepository productRepository;

  public ProductResponse getProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(NotFoundProductException::new);
    return ProductResponse.from(product);
  }

  public CursorPage<ProductResponse> getProducts(Long cursor, int size) {
    int limit = Math.max(1, size);
    Pageable pageable = PageRequest.of(0, limit + 1, Sort.by("productId").ascending());

    Slice<Product> products = (cursor == null)
        ? productRepository.findAllByOrderByProductIdAsc(pageable)
        : productRepository.findByProductIdGreaterThanOrderByProductIdAsc(cursor, pageable);

    boolean hasNext = products.hasNext();
    List<ProductResponse> responses = products.getContent()
            .stream()
            .map(ProductResponse::from)
            .toList();

    return new CursorPage<>(responses, hasNext);
  }

}
