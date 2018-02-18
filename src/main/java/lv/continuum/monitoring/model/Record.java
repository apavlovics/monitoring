package lv.continuum.monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Record {

    public static enum RecordType {
        LOGIN, LOGOUT;
    }

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @JoinColumn(name = "accountId", nullable = false)
    @ManyToOne(optional = false)
    private Account account;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecordType recordType;
}
