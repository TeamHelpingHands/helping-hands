package site.hhsa.demo.volunteers.models;

import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.volunteers.models.Volunteer;

import javax.persistence.*;

@Entity
@Table()
public class FeedbackFromVols {

    //columns

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "date_created", columnDefinition = "TIMESTAMP")
    private String dateCreated;

    //relationships

    @ManyToOne
    @JoinColumn(name="vol_id")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name="org_id")
    private Organization organization;

    //constructors

    public FeedbackFromVols() {
    }

    //getters and setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
