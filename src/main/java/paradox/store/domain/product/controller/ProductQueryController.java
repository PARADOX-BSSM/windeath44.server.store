package paradox.store.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import paradox.store.domain.product.dto.ProductResponse;
import paradox.store.domain.product.service.ProductQueryService;
import paradox.store.global.dto.CursorPage;
import paradox.store.global.dto.ResponseDto;
import paradox.store.global.util.HttpUtil;

@RestController
@RequestMapping("/store/products")
@RequiredArgsConstructor
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    /**
     * Get Product By Id
     */

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDto<ProductResponse>> getProduct(
        @PathVariable("productId") Long productId
    ) {
        ProductResponse response = productQueryService.getProduct(productId);
        ResponseDto<ProductResponse> responseDto = HttpUtil.success("successfully fetched product", response);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Get Products (Cursor Pagination)
     */

    @GetMapping
    public ResponseEntity<ResponseDto<CursorPage<ProductResponse>>> getProducts(
        @RequestParam(value = "cursor", required = false) Long cursor,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        CursorPage<ProductResponse> response = productQueryService.getProducts(cursor, size);
        ResponseDto<CursorPage<ProductResponse>> responseDto =
            HttpUtil.success("successfully fetched products", response);
        return ResponseEntity.ok(responseDto);
    }
}
