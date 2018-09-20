package site.hhsa.demo.volunteers.models;

import javax.persistence.*;

@Entity
@Table(name = "profile_details")
public class VolunteerDetails {

    // ===== Properties & Relationship ===== \\

    @Id
    @GeneratedValue
    private long id;

    @Column (name = "user_id")
    private long userId;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column (name = "photo_url", columnDefinition = "LONGTEXT")
    private String photoUrl;

    @OneToOne
    private Volunteer volunteer;

    // ===== Constructors ===== \\

    public VolunteerDetails(){

    }

    public VolunteerDetails(long userId, String firstName, String lastName, String bio, String photoUrl, Volunteer volunteer) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.photoUrl = photoUrl;
        this.volunteer = volunteer;
    }

    // ===== Getters & Setters ===== \\

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

}
