package kg.laponandsweezy.registrationlapon.entity;

import jakarta.persistence.*;
import kg.laponandsweezy.registrationlapon.enums.Level;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "GeographicHierarchy")
@Getter
@Setter
public class GeographicHierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private GeographicHierarchy parent;

    @Column(name = "name_ru", nullable = false)
    private String nameRu;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;
}
