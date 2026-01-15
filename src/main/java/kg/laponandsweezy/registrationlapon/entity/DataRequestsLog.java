package kg.laponandsweezy.registrationlapon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "DataRequestsLog")
@Getter
@Setter
public class DataRequestsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requesting_agency_id", nullable = false)
    private Agency requestingAgency;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    @Column(name = "request_timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp requestTimestamp;

    @Column(name = "data_fields_requested", columnDefinition = "text")
    private String dataFieldsRequested;

    @Column(name = "purpose_code")
    private String purposeCode;
}
