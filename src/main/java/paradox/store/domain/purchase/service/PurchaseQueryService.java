package paradox.store.domain.purchase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paradox.store.domain.purchase.dto.PurchaseResponse;
import paradox.store.domain.purchase.model.Purchase;
import paradox.store.domain.purchase.repository.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseQueryService {

    private final PurchaseRepository purchaseRepository;

    public List<PurchaseResponse> getPurchasesByUserId(String userId, Long cursor, int size) {
        Slice<Long> purchaseIds;
        
        if (cursor == null) {
            purchaseIds = purchaseRepository.findPurchaseIdsByUserId(userId, PageRequest.of(0, size));
        } else {
            purchaseIds = purchaseRepository.findPurchaseIdsByUserIdAndCursor(userId, cursor, PageRequest.of(0, size));
        }
        
        if (purchaseIds.isEmpty()) {
            return List.of();
        }
        
        List<Purchase> purchases = purchaseRepository.findAllByPurchaseIdsWithProduct(purchaseIds.getContent());
        
        return purchases.stream()
                .map(PurchaseResponse::from)
                .toList();
    }
}
