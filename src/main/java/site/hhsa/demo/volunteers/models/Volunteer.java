package site.hhsa.demo.volunteers.models;

import site.hhsa.demo.organizations.models.Category;
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

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phn_number", nullable = false)
    private String phNum;

    @Column(name = "zipcode", nullable = false)
    private String zipCode;

    @Column(name = "date_created", nullable = false, columnDefinition = "DATE")
    private String dateCreated;

    @Column(name = "is_suspended", nullable = false)
    private boolean isSuspended;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @OneToOne(mappedBy = "volunteer")
    private VolunteerDetails volunteerDetails;

    @ManyToMany(mappedBy = "volunteers")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private List<Category> categories;

    // ===== Constructors ===== \\

    public Volunteer (){}

    public Volunteer(String email, String username, String password, String phNum, String zipCode) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phNum = phNum;
        this.zipCode = zipCode;
    }

    public Volunteer(String email, String username, String password, String phNum, String zipCode, String dateCreated, boolean isSuspended, boolean isAdmin, VolunteerDetails volunteerDetails, List<Event> events, List<Category> categories) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phNum = phNum;
        this.zipCode = zipCode;
        this.dateCreated = dateCreated;
        this.isSuspended = isSuspended;
        this.isAdmin = isAdmin;
        this.volunteerDetails = volunteerDetails;
        this.events = events;
        this.categories = categories;
    }

    // ===== Getters & Setters ===== \\

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public VolunteerDetails getVolunteerDetails() {
        return volunteerDetails;
    }

    public void setVolunteerDetails(VolunteerDetails volunteerDetails) {
        this.volunteerDetails = volunteerDetails;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
