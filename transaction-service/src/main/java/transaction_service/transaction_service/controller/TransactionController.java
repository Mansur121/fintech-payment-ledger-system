package transaction_service.transaction_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transaction_service.transaction_service.dto.TransferRequest;
import transaction_service.transaction_service.service.TransactionService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Map<String, String>> transfer(@Valid @RequestBody TransferRequest request) {
        log.info("Transfer request received: from={} to={} amount={}",
                request.getFromAccount(), request.getToAccount(), request.getAmount());

        String transactionId = transactionService.transfer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("transactionId", transactionId));
    }
}
