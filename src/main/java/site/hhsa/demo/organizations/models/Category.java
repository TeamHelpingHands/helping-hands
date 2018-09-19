package site.hhsa.demo.organizations.models;

import site.hhsa.demo.volunteers.models.Volunteer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    // ===== Properties & Relationship ===== \\

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "volunteers")
    private Volunteer volunteer;

    @ManyToMany
    @JoinTable(
            name = "categories_orgs",
            joinColumns = {@JoinColumn(name = "organizations")},
            inverseJoinColumns = {@JoinColumn(name = "categories")}
    )
    private List<Organization> organizations;

    // ===== Constructors ===== \\

    public Category() {
    }

    public Category(String category, Volunteer volunteer, List<Organization> organizations) {
        this.category = category;
        this.volunteer = volunteer;
        this.organizations = organizations;
    }

    // ===== Getters & Setters ===== \\

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Volunteer getVolunteers() {
        return volunteer;
    }

    public void setVolunteers(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
