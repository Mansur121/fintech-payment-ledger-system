package account_service.account_service.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import account_service.account_service.dto.AccountRequestDTO;
import account_service.account_service.dto.AccountResponseDTO;
import account_service.account_service.dto.BalanceRequestDTO;
import account_service.account_service.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponseDTO add(@RequestBody AccountRequestDTO request) {
        return accountService.add(request);
    }

    @GetMapping
    public List<AccountResponseDTO> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public AccountResponseDTO get(@PathVariable Long id) {
        return accountService.get(id);
    }

    @PutMapping("/{id}")
    public AccountResponseDTO update(@PathVariable Long id, @RequestBody AccountRequestDTO request) {
        return accountService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }

    @PostMapping("/balance")
    public BigDecimal getBalance(@RequestBody BalanceRequestDTO request) {
        return accountService.findbalancebyaccountnumber(request.getAccountNumber());
    }
}
