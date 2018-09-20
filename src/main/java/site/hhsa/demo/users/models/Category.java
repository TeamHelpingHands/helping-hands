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

    //constructor


    public Category() {
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
