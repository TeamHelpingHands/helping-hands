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

    @ManyToMany
    @JoinTable(
            name = "categories_vols",
            joinColumns = {@JoinColumn(name = "volunteer_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Volunteer> volunteers;

    @ManyToMany
    @JoinTable(
            name = "categories_vols",
            joinColumns = {@JoinColumn(name = "organization_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Volunteer> organizations;

    // ===== Constructors ===== \\



    // ===== Getters & Setters ===== \\

}
