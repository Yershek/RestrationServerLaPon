package kg.laponandsweezy.registrationlapon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Marriages")
@Getter
@Setter
public class Marriage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "husband_id", nullable = false)
    private Citizen husband;

    @ManyToOne
    @JoinColumn(name = "wife_id", nullable = false)
    private Citizen wife;

    @ManyToOne
    @JoinColumn(name = "marital_status_id")
    private MaritalStatus maritalStatus;

    @Column(name = "marriage_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date marriageDate;

    @Column(name = "registration_authority", nullable = false)
    private String registrationAuthority;

    @Column(name = "document_series_number", unique = true)
    private String documentSeriesNumber;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive;

    @Column(name = "divorce_date")
    @Temporal(TemporalType.DATE)
    private Date divorceDate;
}
