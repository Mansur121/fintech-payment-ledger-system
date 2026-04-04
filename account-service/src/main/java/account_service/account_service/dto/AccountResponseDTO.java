package account_service.account_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

    private Long id;
    private String name;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime createdDate;
}
