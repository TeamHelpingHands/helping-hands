package site.hhsa.demo.organizations.models;

import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.volunteers.models.Volunteer;

import javax.persistence.*;

@Entity
@Table(name = "feedback_from_orgs")
public class FeedbackFromOrganization {

    // ===== Properties & Relationship ===== \\

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "did_attend", nullable = false)
    private boolean didAttend = false;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    @Column( name="date_created", nullable = false, columnDefinition = "DATE")
    private String dateCreated;

    @Column (name = "flags", columnDefinition = "VARCHAR(255)")
    private String flags;

    @Column(columnDefinition = "INT")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization org;

    @ManyToOne
    @JoinColumn(name = "vol_id")
    private Volunteer volunteer;

    // ===== Constructors ===== \\

    public FeedbackFromOrganization(){

    }

    public FeedbackFromOrganization(boolean didAttend, String feedback, String dateCreated, String flags, int rating, Event event, Organization org, Volunteer volunteer) {
        this.didAttend = didAttend;
        this.feedback = feedback;
        this.dateCreated = dateCreated;
        this.flags = flags;
        this.rating = rating;
        this.event = event;
        this.org = org;
        this.volunteer = volunteer;
    }

    // ===== Getters & Setters ===== \\

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDidAttend() {
        return didAttend;
    }

    public void setDidAttend(boolean didAttend) {
        this.didAttend = didAttend;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}