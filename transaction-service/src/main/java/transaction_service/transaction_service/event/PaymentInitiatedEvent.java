package transaction_service.transaction_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInitiatedEvent {

    private UUID transactionId;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}
