package lv.continuum.monitoring.repo;

import lv.continuum.monitoring.model.Account;
import lv.continuum.monitoring.model.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {

    List<Record> findByAccountOrderByCreatedAtDesc(Account account);
}
