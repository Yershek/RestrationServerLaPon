package kg.laponandsweezy.registrationlapon.entity;

import jakarta.persistence.*;
import kg.laponandsweezy.registrationlapon.enums.ContactType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CitizenContacts")
@Getter
@Setter
public class CitizenContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", nullable = false)
    private ContactType contactType;

    @Column(name = "contact_value", nullable = false)
    private String contactValue;

    @Column(name = "is_primary", columnDefinition = "boolean default false")
    private boolean isPrimary;
}
