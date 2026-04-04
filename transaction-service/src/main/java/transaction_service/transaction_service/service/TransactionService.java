package transaction_service.transaction_service.service;

import transaction_service.transaction_service.dto.TransferRequest;

public interface TransactionService {

    String transfer(TransferRequest request);
}
