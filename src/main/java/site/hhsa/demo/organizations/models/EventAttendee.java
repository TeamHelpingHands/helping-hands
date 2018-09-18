package site.hhsa.demo.organizations.models;


import javax.persistence.*;

@Entity
@Table(name = "event_attendees")
public class EventAttendee {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="event")
    private Event event;

}
