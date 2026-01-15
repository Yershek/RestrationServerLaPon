package kg.laponandsweezy.registrationlapon.entity;

import jakarta.persistence.*;
import kg.laponandsweezy.registrationlapon.enums.ChangeType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "DocumentChangesLog")
@Getter
@Setter
public class DocumentChangesLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @Column(name = "change_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp changeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "change_type", nullable = false)
    private ChangeType changeType;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @Column(columnDefinition = "text")
    private String reason;
}
