package kg.laponandsweezy.registrationlapon.entity;

import jakarta.persistence.*;
import kg.laponandsweezy.registrationlapon.enums.MaritalStatusEnums;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MaritalStatus")
@Getter
@Setter
public class MaritalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private MaritalStatusEnums name;
}
