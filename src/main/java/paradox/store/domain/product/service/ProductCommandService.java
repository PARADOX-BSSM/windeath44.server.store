package paradox.store.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paradox.store.domain.product.domain.Product;
import paradox.store.domain.product.dto.ProductCreateRequest;
import paradox.store.domain.product.mapper.ProductMapper;
import paradox.store.domain.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductCommandService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Transactional
  public void createProduct(ProductCreateRequest request) {
    Product product = productMapper.toProduct(request);
    productRepository.save(product);
  }
}
