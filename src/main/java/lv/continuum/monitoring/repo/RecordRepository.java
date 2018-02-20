package lv.continuum.monitoring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.continuum.monitoring.model.Account;
import lv.continuum.monitoring.model.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByAccountOrderByCreatedAtDesc(Account account);
}
