package transaction_service.transaction_service.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import transaction_service.transaction_service.dto.TransferRequest;
import transaction_service.transaction_service.entity.Transaction;
import transaction_service.transaction_service.event.PaymentInitiatedEvent;
import transaction_service.transaction_service.producer.PaymentProducer;
import transaction_service.transaction_service.repository.TransactionRepository;
import transaction_service.transaction_service.service.TransactionService;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentProducer paymentProducer;

    @Override
    public String transfer(TransferRequest request) {

        // 1. Build and save transaction with PENDING status
        Transaction transaction = Transaction.builder()
                .fromAccount(request.getFromAccount())
                .toAccount(request.getToAccount())
                .amount(request.getAmount())
                .status("PENDING")
                .build();

        Transaction saved = transactionRepository.save(transaction);
        log.info("Transaction saved with id={} status=PENDING", saved.getId());

        // 2. Publish Kafka event
        PaymentInitiatedEvent event = PaymentInitiatedEvent.builder()
                .transactionId(saved.getId())
                .fromAccount(saved.getFromAccount())
                .toAccount(saved.getToAccount())
                .amount(saved.getAmount())
                .build();

        paymentProducer.publishPaymentInitiated(event);

        // 3. Return transactionId as String
        return saved.getId().toString();
    }
}
