package site.hhsa.demo.volunteers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.hhsa.demo.volunteers.models.Volunteer;

import java.util.List;

@Repository
public interface VolunteerRepo extends CrudRepository<Volunteer, Long> {


}
