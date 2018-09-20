package site.hhsa.demo.users.repositories;

import org.springframework.data.repository.CrudRepository;
import site.hhsa.demo.users.models.User;

public interface UserRepo extends CrudRepository<User, Long> {
}
