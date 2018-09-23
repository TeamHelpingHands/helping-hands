package site.hhsa.demo.volunteers.models;

import site.hhsa.demo.organizations.models.FeedbackFromOrganization;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.organizations.models.Event;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "volunteers")
public class Volunteer {

    // ===== Properties & Relationship ===== \\

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP")
    private String dateCreated;

    @Column(name = "is_suspended", nullable = false)
    private boolean isSuspended;

    @Column(columnDefinition = "LONGTEXT")
    private String bio;

    //relationships

    @OneToOne
    private User user;

    @ManyToMany(mappedBy = "volunteers")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private List<FeedbackFromOrganization> orgFeedback;

    @ManyToMany(mappedBy = "followers")
    private List<Organization> favorites;

    // ===== Constructors ===== \\


    public Volunteer() {
    }

    public Volunteer(String dateCreated, boolean isSuspended, String bio, User user, List<Event> events, List<FeedbackFromOrganization> orgFeedback, List<Organization> favorites) {
        this.dateCreated = dateCreated;
        this.isSuspended = isSuspended;
        this.bio = bio;
        this.user = user;
        this.events = events;
        this.orgFeedback = orgFeedback;
        this.favorites = favorites;
    }

    // getters & setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<FeedbackFromOrganization> getOrgFeedback() {
        return orgFeedback;
    }

    public void setOrgFeedback(List<FeedbackFromOrganization> orgFeedback) {
        this.orgFeedback = orgFeedback;
    }

    public List<Organization> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Organization> favorites) {
        this.favorites = favorites;
    }
}
