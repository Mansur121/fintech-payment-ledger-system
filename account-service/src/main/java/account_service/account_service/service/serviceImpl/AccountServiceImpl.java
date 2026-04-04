package account_service.account_service.service.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import account_service.account_service.dto.AccountRequestDTO;
import account_service.account_service.dto.AccountResponseDTO;
import account_service.account_service.entity.UserAccount;
import account_service.account_service.exception.AccountNotFoundException;
import account_service.account_service.repository.AccountRepository;
import account_service.account_service.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // ── Entity ↔ DTO helpers ──────────────────────────────────────────

    private UserAccount toEntity(AccountRequestDTO dto) {
        UserAccount account = new UserAccount();
        account.setName(dto.getName());
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());
        return account;
    }

    private AccountResponseDTO toResponse(UserAccount account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getName(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCreatedDate()
        );
    }

    // ── Service methods ───────────────────────────────────────────────

    @Override
    public AccountResponseDTO add(AccountRequestDTO request) {
        UserAccount saved = accountRepository.save(toEntity(request));
        return toResponse(saved);
    }

    @Override
    public List<AccountResponseDTO> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO update(Long id, AccountRequestDTO request) {
        UserAccount existing = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(id)));
        existing.setName(request.getName());
        existing.setAccountNumber(request.getAccountNumber());
        existing.setBalance(request.getBalance());
        return toResponse(accountRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        UserAccount existing = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(id)));
        accountRepository.delete(existing);
    }

    @Override
    public AccountResponseDTO get(Long id) {
        return toResponse(accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(id))));
    }

    @Override
    public BigDecimal findbalancebyaccountnumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }
}
