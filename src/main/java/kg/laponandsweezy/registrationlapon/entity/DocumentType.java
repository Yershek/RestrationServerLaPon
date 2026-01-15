package kg.laponandsweezy.registrationlapon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DocumentTypes")
@Getter
@Setter
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_ru", nullable = false, unique = true)
    private String nameRu;

    @Column(columnDefinition = "TEXT")
    private String description;
}
