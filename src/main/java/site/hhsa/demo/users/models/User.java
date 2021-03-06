package site.hhsa.demo.users.models;

import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.volunteers.models.Volunteer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    //Columns

    @Id
    @GeneratedValue
    private long id;

    @Column( nullable = false, unique = true)
    private String email;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="phn_num", nullable = false, unique = true)
    private String phnNum;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name="photo_url")
    private String photoUrl = "http://swasti.org/wp-content/plugins/awsm-team-pro/images/default-user.png";

    @Column(nullable = false)
    private String zipcode;

    @Column(name="is_admin")
    private boolean isAdmin = false;

    @Column(name="is_org")
    private boolean isOrg = false;

    @Column(name="date_created", nullable = false, columnDefinition = "TIMESTAMP")
    private String dateCreated;

    //Relationships

    @OneToOne(mappedBy = "user")
    private Organization organization;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Volunteer volunteer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Category> categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Message> sentMessages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private List<Message> receivedMessaged;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporter")
    private List<Report> reportsMade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "violator")
    private List<Report> reportsReceived;

    @ManyToMany(mappedBy = "followers")
    private List<Organization> favorites;

    //constructors


    public User() {
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User(String email, String firstName, String lastName, String phnNum, String username, String password, String photoUrl, String zipcode, boolean isAdmin, boolean isOrg, String dateCreated, Organization organization, Volunteer volunteer, List<Category> categories, List<Message> sentMessages, List<Message> receivedMessaged, List<Report> reportsMade, List<Report> reportsReceived, List<Organization> favorites) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phnNum = phnNum;
        this.username = username;
        this.password = password;
        this.photoUrl = photoUrl;
        this.zipcode = zipcode;
        this.isAdmin = isAdmin;
        this.isOrg = isOrg;
        this.dateCreated = dateCreated;
        this.organization = organization;
        this.volunteer = volunteer;
        this.categories = categories;
        this.sentMessages = sentMessages;
        this.receivedMessaged = receivedMessaged;
        this.reportsMade = reportsMade;
        this.reportsReceived = reportsReceived;
        this.favorites = favorites;
    }

    //getters & setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhnNum() {
        return phnNum;
    }

    public void setPhnNum(String phnNum) {
        this.phnNum = phnNum;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isOrg() {
        return isOrg;
    }

    public void setOrg(boolean org) {
        isOrg = org;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessaged() {
        return receivedMessaged;
    }

    public void setReceivedMessaged(List<Message> receivedMessaged) {
        this.receivedMessaged = receivedMessaged;
    }

    public List<Report> getReportsMade() {
        return reportsMade;
    }

    public void setReportsMade(List<Report> reportsMade) {
        this.reportsMade = reportsMade;
    }

    public List<Report> getReportsReceived() {
        return reportsReceived;
    }

    public void setReportsReceived(List<Report> reportsReceived) {
        this.reportsReceived = reportsReceived;
    }

    public List<Organization> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Organization> favorites) {
        this.favorites = favorites;
    }
}
