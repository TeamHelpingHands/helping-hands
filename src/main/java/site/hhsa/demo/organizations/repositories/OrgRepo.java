package site.hhsa.demo.organizations.repositories;

import org.springframework.data.repository.CrudRepository;
import site.hhsa.demo.organizations.models.Organization;

public interface OrgRepo extends CrudRepository<Organization, Long> {
}
