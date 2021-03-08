package database.entities;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="mail")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Getter
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String subject;

    @Column(name = "date")
    private String localDateTime;

    @Column
    private String type;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sent_from", referencedColumnName="id")
    private EmailAddress sentFrom;

    @ManyToMany
    @JoinTable(
            name="mail_address",
            joinColumns = @JoinColumn(name="id_mail", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="id_address", referencedColumnName = " id")

    )
    private List<EmailAddress> emailAddressList;

    public Email( String subject, String localDateTime, String type, String content, EmailAddress sentFrom ) {
        this.subject = subject;
        this.localDateTime = localDateTime;
        this.type = type;
        this.content = content;
        this.sentFrom = sentFrom;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", localDateTime='" + localDateTime + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", sentFrom=" + sentFrom +
                '}';
    }
}
