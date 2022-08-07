package info.shkryl.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "subscribe")
@Getter
@Setter
public class Subscribe {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    @ManyToMany
    @JoinTable(name = "userinfo_subscribe",
            joinColumns = @JoinColumn(name = "subscribe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> userList = new HashSet<>();
}