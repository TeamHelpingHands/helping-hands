package site.hhsa.demo.users.models;

import javax.persistence.*;

@Entity
@Table(name = "reports")
public class Report {

    //columns

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String reasons;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP")
    private String dateCreated;

    //relationships

    @ManyToOne
    @JoinColumn(name="reporter_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name="violator_id")
    private User violator;


}
