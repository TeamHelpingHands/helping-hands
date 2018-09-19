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

    @Column(name = "vol_id")
    private long volunteerId;

    @Column(name = "org_id")
    private long orgId;

    @Column(name = "rating", nullable = false, )
    private int rating;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    @Column( name="date_created", nullable = false, columnDefinition = "DATE")
    private String dateCreated;

    @Column (name = "flags", columnDefinition = "VARCHAR(255)")
    private String flags;

    @ManyToMany
    @JoinColumn(name = "org_id")
    private Organization org;

    // ===== Constructors ===== \\

    public FeedbackFromOrganization(){

    }


    public FeedbackFromOrganization(long volunteerId, long orgId, int rating, String feedback, String dateCreated, String flags) {
        this.volunteerId = volunteerId;
        this.orgId = orgId;
        this.rating = rating;
        this.feedback = feedback;
        this.dateCreated = dateCreated;
        this.flags = flags;
    }

    public FeedbackFromOrganization(long volunteerId, long orgId, int rating, String feedback, String dateCreated, String flags, Organization org) {
        this.volunteerId = volunteerId;
        this.orgId = orgId;
        this.rating = rating;
        this.feedback = feedback;
        this.dateCreated = dateCreated;
        this.flags = flags;
        this.org = org;
    }

    // ===== Getters & Setters ===== \\

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
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