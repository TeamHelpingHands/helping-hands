package site.hhsa.demo.organizations.models;

import site.hhsa.demo.users.models.User;
import site.hhsa.demo.volunteers.models.FeedbackFromVols;
import site.hhsa.demo.volunteers.models.Volunteer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization {

    // ===== Properties & Relationship ===== \\

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "org_name", nullable = false)
    private String orgName;

    @Column(name="about_us",columnDefinition = "TEXT")
    private String aboutUs;

    @Column(name="str_addr", nullable = false)
    private String strAddr;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "tax_id", nullable = false)
    private String taxId;

    @Column(name="is_validated", nullable = false)
    private Boolean isValidated = false;

    //relationships

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "org")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "org")
    private List<FeedbackFromOrganization> orgFeedback;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private List<FeedbackFromVols> volFeedback;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "likes",
            joinColumns = {@JoinColumn(name="followers")},
            inverseJoinColumns = {@JoinColumn(name="favorites")}
    )
    private List<User> followers;


    // ===== Constructors ===== \\


    public Organization() {
    }

    public Organization(String orgName, String aboutUs, String strAddr, String city, String state, String taxId) {
        this.orgName = orgName;
        this.aboutUs = aboutUs;
        this.strAddr = strAddr;
        this.city = city;
        this.state = state;
        this.taxId = taxId;
    }

    public Organization(String orgName, String aboutUs, String strAddr, String city, String state, String taxId, Boolean isValidated, User user, List<Event> events, List<FeedbackFromOrganization> orgFeedback, List<FeedbackFromVols> volFeedback, List<User> followers) {
        this.orgName = orgName;
        this.aboutUs = aboutUs;
        this.strAddr = strAddr;
        this.city = city;
        this.state = state;
        this.taxId = taxId;
        this.isValidated = isValidated;
        this.user = user;
        this.events = events;
        this.orgFeedback = orgFeedback;
        this.volFeedback = volFeedback;
        this.followers = followers;
    }

    //getters  & setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStrAddr() {
        return strAddr;
    }

    public void setStrAddr(String strAddr) {
        this.strAddr = strAddr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Boolean getValidated() {
        return isValidated;
    }

    public void setValidated(Boolean validated) {
        isValidated = validated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public List<FeedbackFromVols> getVolFeedback() {
        return volFeedback;
    }

    public void setVolFeedback(List<FeedbackFromVols> volFeedback) {
        this.volFeedback = volFeedback;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
