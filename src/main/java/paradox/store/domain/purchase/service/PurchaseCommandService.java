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
        Product product = getProduct(request.productId());
        Long totalPrice = product.getMoney() * request.quantity();
        
        validateAndDeductMoney(request.userId(), totalPrice);
        savePurchaseHistory(request, product, totalPrice);
        addItemToInventory(request.userId(), product, request.quantity());
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::getInstance);
    }

    private void validateAndDeductMoney(String userId, Long totalPrice) {
        User user = userClient.findById(userId);
        if (user.getMoney() < totalPrice) {
            throw InsufficientMoneyException.getInstance();
        }
        userClient.deductMoney(userId, totalPrice);
    }

    private void savePurchaseHistory(PurchaseRequest request, Product product, Long totalPrice) {
        Purchase purchase = Purchase.builder()
                .userId(request.userId())
                .product(product)
                .quantity(request.quantity())
                .totalPrice(totalPrice)
                .createdAt(LocalDateTime.now())
                .build();
        purchaseRepository.save(purchase);
    }

    private void addItemToInventory(String userId, Product product, Long quantity) {
        Inventory inventory = getOrCreateInventory(userId);
        
        if (product.getIsStackable()) {
            addStackableItem(inventory, product, quantity);
        } else {
            addNonStackableItems(inventory, product, quantity);
        }
    }

    private Inventory getOrCreateInventory(String userId) {
        return inventoryRepository.findByUserId(userId)
                .orElseGet(() -> inventoryRepository.save(
                        Inventory.builder().userId(userId).build()
                ));
    }

    private void addStackableItem(Inventory inventory, Product product, Long quantity) {
        itemRepository.findByInventoryAndProduct(inventory, product)
                .ifPresentOrElse(
                        item -> item.increaseAmount(quantity),
                        () -> saveNewItem(inventory, product, quantity)
                );
    }

    private void addNonStackableItems(Inventory inventory, Product product, Long quantity) {
        for (int i = 0; i < quantity; i++) {
            saveNewItem(inventory, product, 1L);
        }
    }

    private void saveNewItem(Inventory inventory, Product product, Long amount) {
        Item item = Item.builder()
                .inventory(inventory)
                .product(product)
                .acquiredAt(LocalDateTime.now())
                .amount(amount)
                .build();
        itemRepository.save(item);
    }
}
