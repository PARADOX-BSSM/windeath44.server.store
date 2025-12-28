package paradox.store.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paradox.store.domain.product.model.Product;
import paradox.store.domain.product.dto.ProductCreateRequest;
import paradox.store.domain.product.dto.ProductUpdateRequest;
import paradox.store.domain.product.exception.NotFoundProductException;
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

  @Transactional
  public void updateProduct(Long productId, ProductUpdateRequest request) {
     Product product = productRepository.findById(productId)
         .orElseThrow(NotFoundProductException::getInstance);
     product.update(request.name(), request.description(), request.sumnailUrl(), request.isStackable());
  }

  @Transactional
  public void deleteProduct(Long productId) {
      Product product = productRepository.findById(productId)
                      .orElseThrow(NotFoundProductException::getInstance);
      productRepository.delete(product);
  }
}
