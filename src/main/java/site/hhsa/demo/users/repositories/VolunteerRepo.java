package site.hhsa.demo.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.hhsa.demo.users.models.Volunteer;

import java.util.List;

@Repository
public interface VolunteerRepo extends CrudRepository<Volunteer, Long> {

    List<Volunteer> findAll();

    Volunteer findByUsername(String username);

}
