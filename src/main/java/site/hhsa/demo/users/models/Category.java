package site.hhsa.demo.users.models;

import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.volunteers.models.Volunteer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    //columns

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "category")
    private String category;

    //relationships

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
