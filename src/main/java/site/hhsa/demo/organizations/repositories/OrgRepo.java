package site.hhsa.demo.organizations.repositories;

import org.springframework.data.repository.CrudRepository;
import site.hhsa.demo.organizations.models.Organization;

import java.util.List;

public interface OrgRepo extends CrudRepository<Organization, Long> {
    List<Organization> findAll();

    Organization findOrganizationByOrgName(String orgName);

}
