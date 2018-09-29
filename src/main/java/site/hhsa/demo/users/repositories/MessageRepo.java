package site.hhsa.demo.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.hhsa.demo.users.models.Message;
import site.hhsa.demo.users.models.User;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findAllByReceiverOrderByTimeSentDesc(User user);
    List<Message> findAllBySenderOrderByTimeSentDesc(User user);

}
