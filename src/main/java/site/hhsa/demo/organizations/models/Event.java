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

    @Column(name="event_name")
    private String eventName;

    @Column(nullable = false, columnDefinition = "DATE")
    private String date;

    @Column(nullable = false, columnDefinition = "TIME")
    private String time;

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

    public Event(String description, String eventName, String date, String time, String strAddr, String zipCode, Organization org, List<FeedbackFromOrganization> feedback, List<Volunteer> volunteers) {
        this.description = description;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.strAddr = strAddr;
        this.zipCode = zipCode;
        this.org = org;
        this.feedback = feedback;
        this.volunteers = volunteers;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
