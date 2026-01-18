package paradox.store.domain.purchase.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paradox.store.domain.purchase.model.Purchase;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("SELECT p FROM Purchase p JOIN FETCH p.product WHERE p.userId = :userId ORDER BY p.createdAt DESC")
    List<Purchase> findByUserIdWithProduct(@Param("userId") String userId);

    @Query("SELECT p.purchaseId FROM Purchase p WHERE p.userId = :userId ORDER BY p.purchaseId DESC")
    Slice<Long> findPurchaseIdsByUserId(@Param("userId") String userId, Pageable pageable);

    @Query("SELECT p.purchaseId FROM Purchase p WHERE p.userId = :userId AND p.purchaseId < :cursor ORDER BY p.purchaseId DESC")
    Slice<Long> findPurchaseIdsByUserIdAndCursor(@Param("userId") String userId, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT p FROM Purchase p JOIN FETCH p.product WHERE p.purchaseId IN :purchaseIds ORDER BY p.purchaseId DESC")
    List<Purchase> findAllByPurchaseIdsWithProduct(@Param("purchaseIds") List<Long> purchaseIds);
}
