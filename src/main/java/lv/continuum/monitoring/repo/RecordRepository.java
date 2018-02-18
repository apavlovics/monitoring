package lv.continuum.monitoring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.continuum.monitoring.model.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {}
