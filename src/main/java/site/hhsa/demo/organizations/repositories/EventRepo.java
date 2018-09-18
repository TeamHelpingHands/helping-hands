package site.hhsa.demo.organizations.repositories;

import org.springframework.data.repository.CrudRepository;
import site.hhsa.demo.organizations.models.Event;

import java.util.List;

public interface EventRepo extends CrudRepository<Event, Long> {
    List<Event> findAll();

    Event findEventByEventName(String eventName);
}
