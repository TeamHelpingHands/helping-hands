package site.hhsa.demo.volunteers.models;

import javax.persistence.*;

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
    private String phnNumber;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "date_created", nullable = false)
    private String dateCreated;

    @Column(name = "is_suspended", nullable = false)
    private boolean isSuspended;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @OneToOne(mappedBy = "volunteer")
    private VolunteerDetails volunteerDetails;

    // ===== Constructors ===== \\

    public Volunteer (){}

    public Volunteer(String email, String username, String password, String phnNumber, String zipcode) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phnNumber = phnNumber;
        this.zipcode = zipcode;
    }

    public Volunteer(long id, String email, String username, String password, String phnNumber, String zipcode, String dateCreated, boolean isSuspended, boolean isAdmin, VolunteerDetails volunteerDetails) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phnNumber = phnNumber;
        this.zipcode = zipcode;
        this.dateCreated = dateCreated;
        this.isSuspended = isSuspended;
        this.isAdmin = isAdmin;
        this.volunteerDetails = volunteerDetails;
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

    public String getPhnNumber() {
        return phnNumber;
    }

    public void setPhnNumber(String phnNumber) {
        this.phnNumber = phnNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

}
