package database.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="mail_address")
    private String mailAddress;

    public Address( String mailAddress ) {
        this.mailAddress = mailAddress;
    }
    @ManyToMany(mappedBy = "addressList")
    List<Mail> mailList;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }
}
