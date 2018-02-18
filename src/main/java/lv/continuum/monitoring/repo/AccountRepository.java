package lv.continuum.monitoring.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.continuum.monitoring.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findOne(long accountId);
}
