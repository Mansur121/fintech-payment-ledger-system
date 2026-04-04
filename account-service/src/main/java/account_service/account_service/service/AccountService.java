package account_service.account_service.service;

import java.math.BigDecimal;
import java.util.List;

import account_service.account_service.dto.AccountRequestDTO;
import account_service.account_service.dto.AccountResponseDTO;

public interface AccountService {

    AccountResponseDTO add(AccountRequestDTO request);

    BigDecimal findbalancebyaccountnumber(String accountNumber);

    List<AccountResponseDTO> getAll();

    AccountResponseDTO update(Long id, AccountRequestDTO request);

    void delete(Long id);

    AccountResponseDTO get(Long id);
}
