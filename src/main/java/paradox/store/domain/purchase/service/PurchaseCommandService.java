package paradox.store.domain.purchase.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import paradox.store.domain.inventory.exception.NotFoundInventoryException;
import paradox.store.domain.inventory.model.Inventory;
import paradox.store.domain.inventory.model.Item;
import paradox.store.domain.inventory.repository.InventoryRepository;
import paradox.store.domain.inventory.repository.ItemRepository;
import paradox.store.domain.product.exception.NotFoundProductException;
import paradox.store.domain.product.model.Product;
import paradox.store.domain.product.repository.ProductRepository;
import paradox.store.domain.purchase.dto.PurchaseRequest;
import paradox.store.domain.purchase.model.Purchase;
import paradox.store.domain.purchase.repository.PurchaseRepository;
import paradox.store.domain.purchase.exception.InsufficientMoneyException;
import paradox.store.global.client.UserClient;
import paradox.store.global.client.dto.User;

@Service
@RequiredArgsConstructor
public class PurchaseCommandService {

    private final UserClient userClient;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final PurchaseRepository purchaseRepository;

    @Transactional
    public void purchase(PurchaseRequest request) {
        // 1. 사용자 조회 및 잔액 확인
        User user = userClient.findById(request.userId());
        
        // 2. 상품 조회
        Product product = productRepository.findById(request.productId())
                .orElseThrow(NotFoundProductException::getInstance);
        
        // 3. 총 금액 계산
        Long totalPrice = product.getMoney() * request.quantity();
        
        // 4. 잔액 확인
        if (user.getMoney() < totalPrice) {
            throw InsufficientMoneyException.getInstance();
        }
        
        // 5. 돈 차감
        userClient.deductMoney(request.userId(), totalPrice);
        
        // 6. 구매 내역 저장
        Purchase purchase = Purchase.builder()
                .userId(request.userId())
                .product(product)
                .quantity(request.quantity())
                .totalPrice(totalPrice)
                .createdAt(LocalDateTime.now())
                .build();
        purchaseRepository.save(purchase);
        
        // 7. 인벤토리 조회 (없으면 생성)
        Inventory inventory = inventoryRepository.findByUserId(request.userId())
                .orElseGet(() -> inventoryRepository.save(
                        Inventory.builder()
                                .userId(request.userId())
                                .build()
                ));
        
        // 8. 아이템 추가 (stackable 여부에 따라 처리)
        if (product.getIsStackable()) {
            // stackable: 기존 아이템이 있으면 수량 증가, 없으면 새로 생성
            itemRepository.findByInventoryAndProduct(inventory, product)
                    .ifPresentOrElse(
                            existingItem -> existingItem.increaseAmount(request.quantity()),
                            () -> createNewItem(inventory, product, request.quantity())
                    );
        } else {
            // non-stackable: 수량만큼 개별 아이템 생성
            for (int i = 0; i < request.quantity(); i++) {
                createNewItem(inventory, product, 1L);
            }
        }
    }
    
    private void createNewItem(Inventory inventory, Product product, Long amount) {
        Item item = Item.builder()
                .inventory(inventory)
                .product(product)
                .acquiredAt(LocalDateTime.now())
                .amount(amount)
                .build();
        itemRepository.save(item);
    }
}
