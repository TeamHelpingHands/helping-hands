package site.hhsa.demo.volunteers.repositories;

import org.springframework.data.repository.CrudRepository;
import site.hhsa.demo.volunteers.models.VolunteerDetails;

import java.util.List;

public interface VolunteerDetailsRepo extends CrudRepository<VolunteerDetails, Long> {

    List<VolunteerDetails> findAll();
}
