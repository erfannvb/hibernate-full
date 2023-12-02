package nvb.dev.service;

import nvb.dev.base.service.BaseService;
import nvb.dev.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService extends BaseService<Long, Person> {

    void addPerson(Person person);

    boolean validate(Person person);

    Optional<List<Person>> findAllPersons();

    Optional<Person> findByFirstName(String firstName);

    Optional<Person> findByLastName(String lastName);

}
