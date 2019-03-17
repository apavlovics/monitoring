package lv.continuum.monitoring.repo;

import lv.continuum.monitoring.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {}
