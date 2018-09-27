package site.hhsa.demo.organizations.repositories;

import org.springframework.data.repository.CrudRepository;
import site.hhsa.demo.organizations.models.Event;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.volunteers.models.Volunteer;

import java.util.List;

public interface EventRepo extends CrudRepository<Event, Long> {
    List<Event> findAll();
    List<Event> findAllByVolunteersContains(Volunteer volunteer) ;
    List<Event> findAllByOrg(Organization org);
}
