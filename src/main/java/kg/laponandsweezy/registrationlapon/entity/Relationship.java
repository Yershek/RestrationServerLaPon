package kg.laponandsweezy.registrationlapon.entity;

import jakarta.persistence.*;
import kg.laponandsweezy.registrationlapon.enums.RelationshipType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Relationships")
@Getter
@Setter
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "citizen_a_id", nullable = false)
    private Citizen citizenA;

    @ManyToOne
    @JoinColumn(name = "citizen_b_id", nullable = false)
    private Citizen citizenB;

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship_type", nullable = false)
    private RelationshipType relationshipType;

    @Column(name = "is_verified", columnDefinition = "boolean default false")
    private boolean isVerified;
}
