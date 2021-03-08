package database.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name="address")
public class EmailAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="mail_address", unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private String mailAddress;

    public EmailAddress( String mailAddress ) {
        this.mailAddress = mailAddress;
    }
    @ManyToMany(mappedBy = "emailAddressList")
    List<Email> emailList;

    @Override
    public String toString() {
        return "EmailAddress{" +
                "id=" + id +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }
}
