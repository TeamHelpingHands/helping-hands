package site.hhsa.demo.users.models;

import javax.persistence.*;

@Entity
@Table(name="messages")
public class Message {

    //columns

    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP")
    private String dateCreated;

    //relationships

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    private User receiver;
}
