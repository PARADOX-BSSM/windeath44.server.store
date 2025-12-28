package paradox.store.domain.product.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paradox.store.domain.product.model.Product;
import paradox.store.domain.product.dto.ProductResponse;
import paradox.store.domain.product.exception.NotFoundProductException;
import paradox.store.domain.product.mapper.ProductMapper;
import paradox.store.domain.product.repository.ProductRepository;
import paradox.store.global.dto.CursorPage;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ProductResponse getProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(NotFoundProductException::getInstance);
    return ProductMapper.toResponse(product);
  }

  public CursorPage<ProductResponse> getProducts(Long cursor, int size) {
    int limit = Math.max(1, size);
    Pageable pageable = PageRequest.of(0, limit, Sort.by("productId").ascending());

    Slice<Product> products = (cursor == null)
        ? productRepository.findAllByOrderByProductIdAsc(pageable)
        : productRepository.findByProductIdGreaterThanOrderByProductIdAsc(cursor, pageable);

    boolean hasNext = products.hasNext();

    List<ProductResponse> responses = productMapper.toCursorResponse(products);


    return new CursorPage<>(responses, hasNext);
  }

    public Product findById(Long productId) {
      return productRepository.findById(productId)
        .orElseThrow(NotFoundProductException::getInstance);
    }
}
