package site.hhsa.demo.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.volunteers.models.Volunteer;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
