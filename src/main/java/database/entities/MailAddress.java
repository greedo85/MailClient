package database.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "mail_address")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class MailAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_mail", referencedColumnName = "id")
    private Mail mail;

    @OneToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;

    public MailAddress( Mail mail, Address address ) {
        this.mail = mail;
        this.address = address;
    }

    @Override
    public String toString() {
        return "MailAddress{" +
                "id=" + id +
                ", mail=" + mail +
                ", address=" + address +
                '}';
    }
}
