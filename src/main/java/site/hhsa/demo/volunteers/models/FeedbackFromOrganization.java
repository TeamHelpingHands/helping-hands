package site.hhsa.demo.volunteers.models;

import site.hhsa.demo.organizations.models.Organization;

import javax.persistence.*;

@Entity
@Table(name = "feedback_from_orgs")
public class FeedbackFromOrganization {

    // ===== Properties & Relationship ===== \\

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    @Column( name="date_created", nullable = false, columnDefinition = "DATE")
    private String dateCreated;

    @Column (name = "flags", columnDefinition = "VARCHAR(255)")
    private String flags;

    @ManyToOne
    @JoinColumn(name = "org_feedback")
    private Organization org;

    // ===== Constructors ===== \\

    public FeedbackFromOrganization(){

    }




    // ===== Getters & Setters ===== \\

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

}