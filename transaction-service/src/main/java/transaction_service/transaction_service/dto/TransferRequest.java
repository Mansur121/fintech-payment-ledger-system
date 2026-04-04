package transaction_service.transaction_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @NotBlank(message = "fromAccount must not be blank")
    private String fromAccount;

    @NotBlank(message = "toAccount must not be blank")
    private String toAccount;

    @NotNull(message = "amount must not be null")
    @DecimalMin(value = "0.01", message = "amount must be greater than 0")
    private BigDecimal amount;
}
