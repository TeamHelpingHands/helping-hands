package site.hhsa.demo.organizations.models;


import site.hhsa.demo.volunteers.models.Volunteer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String description;

    @Column(name="photo_url", columnDefinition = "TEXT")
    private String photoUrl;

    @Column(columnDefinition = "TIMESTAMP")
    private String dateTime;

    @Column(name = "str_addr", nullable = false)
    private String strAddr;

    @Column(name="zip_code", nullable = false)
    private String zipCode;

    @ManyToOne
    @JoinColumn(name="organization")
    private Organization org;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<FeedbackFromOrganization> feedback;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_volunteers",
            joinColumns = {@JoinColumn(name="volunteers")},
            inverseJoinColumns = {@JoinColumn(name="events")}

    )
    private List<Volunteer> volunteers;

    public Event() {
    }

    public Event(String description, String photoUrl, String dateTime, String strAddr, String zipCode, Organization org) {
        this.description = description;
        this.photoUrl = photoUrl;
        this.dateTime = dateTime;
        this.strAddr = strAddr;
        this.zipCode = zipCode;
        this.org = org;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStrAddr() {
        return strAddr;
    }

    public void setStrAddr(String strAddr) {
        this.strAddr = strAddr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Organization getOrganization() {
        return org;
    }

    public void setOrganization(Organization organization) {
        this.org= organization;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public List<FeedbackFromOrganization> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<FeedbackFromOrganization> feedback) {
        this.feedback = feedback;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }
}
