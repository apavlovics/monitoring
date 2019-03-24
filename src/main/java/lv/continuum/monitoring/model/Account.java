package lv.continuum.monitoring.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "username"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String username;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Record> records;
}
