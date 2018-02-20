package lv.continuum.monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Record {

    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(name = "accountId", nullable = false)
    @ManyToOne(optional = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecordType recordType;
}
