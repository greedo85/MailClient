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
    private Email email;

    @OneToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private EmailAddress emailAddress;

    public MailAddress( Email email, EmailAddress emailAddress ) {
        this.email = email;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "MailAddress{" +
                "id=" + id +
                ", email=" + email +
                ", emailAddress=" + emailAddress +
                '}';
    }
}
