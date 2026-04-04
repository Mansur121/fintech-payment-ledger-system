package transaction_service.transaction_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transaction_service.transaction_service.entity.Transaction;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
