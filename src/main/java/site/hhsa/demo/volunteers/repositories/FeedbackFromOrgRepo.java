package site.hhsa.demo.volunteers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.hhsa.demo.organizations.models.Event;
import site.hhsa.demo.organizations.models.FeedbackFromOrganization;
import site.hhsa.demo.volunteers.models.Volunteer;

import java.util.List;

@Repository
public interface FeedbackFromOrgRepo extends CrudRepository<FeedbackFromOrganization, Long> {

    List<FeedbackFromOrganization> findAllByVolunteer(Volunteer vol);
    List<FeedbackFromOrganization> findFeedbackFromOrganizationByEvent(Event event);
}
