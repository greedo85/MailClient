package database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="mail")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Mail {

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
    private Address sentFrom;

    @ManyToMany
    @JoinTable(
            name="mail_address",
            joinColumns = @JoinColumn(name="id_mail", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="id_address", referencedColumnName = " id")

    )
    private List<Address>addressList;

    public Mail( String subject, String localDateTime, String type, String content, Address sentFrom ) {
        this.subject = subject;
        this.localDateTime = localDateTime;
        this.type = type;
        this.content = content;
        this.sentFrom = sentFrom;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", localDateTime='" + localDateTime + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", sentFrom=" + sentFrom +
                ", addressList=" + addressList +
                '}';
    }
}