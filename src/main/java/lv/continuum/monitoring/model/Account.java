package lv.continuum.monitoring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "username"})
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 200)
    private String username;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Record> records;
}
