package paradox.store.domain.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paradox.store.global.dto.ResponseDto;
import paradox.store.domain.product.domain.Product;
import paradox.store.domain.product.dto.ProductCreateRequest;
import paradox.store.domain.product.service.ProductCommandService;
import paradox.store.global.util.HttpUtil;

@RestController
@RequestMapping("/store/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandService productService;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> createProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        productService.createProduct(request);
        ResponseDto<Void> responseDto = HttpUtil.success("successfully created product");

        return ResponseEntity.ok(responseDto);
    }
}
