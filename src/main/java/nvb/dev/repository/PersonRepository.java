package nvb.dev.repository;

import nvb.dev.base.repository.BaseRepository;
import nvb.dev.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends BaseRepository<Long, Person> {

    Optional<List<Person>> findAllPersons();

    Optional<Person> findByFirstName(String firstName);

    Optional<Person> findByLastName(String lastName);

}
