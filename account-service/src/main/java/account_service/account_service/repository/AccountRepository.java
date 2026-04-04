package account_service.account_service.repository;

import account_service.account_service.entity.UserAccount;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("SELECT u.balance FROM UserAccount u WHERE u.accountNumber = :accountNumber")
    Optional<BigDecimal> findByAccountNumber(String accountNumber);

}
