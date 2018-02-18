package lv.continuum.monitoring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.continuum.monitoring.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {}
