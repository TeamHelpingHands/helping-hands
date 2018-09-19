package site.hhsa.demo.organizations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.hhsa.demo.organizations.models.Category;

import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {

    List<Category> findAll();

}
