package nvb.dev.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import nvb.dev.base.service.impl.BaseServiceImpl;
import nvb.dev.exception.ValidationException;
import nvb.dev.model.Person;
import nvb.dev.repository.PersonRepository;
import nvb.dev.service.PersonService;
import org.hibernate.Session;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PersonServiceImpl extends BaseServiceImpl<Long, Person, PersonRepository>
        implements PersonService {

    protected final Session session;

    public PersonServiceImpl(Session session, PersonRepository repository) {
        super(session, repository);
        this.session = session;
    }

    @Override
    public void addPerson(Person person) {
        session.getTransaction().begin();
        repository.save(person);
        session.getTransaction().commit();
    }

    @Override
    public boolean validate(Person person) {
        try {

            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory();

            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<Person>> violationSet = validator.validate(person);
            if (!violationSet.isEmpty()) {
                for (ConstraintViolation<Person> violation : violationSet) {
                    System.out.println(violation.getMessage());
                }
                validatorFactory.close();
                return false;
            } else {
                addPerson(person);
                return true;
            }

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<List<Person>> findAllPersons() {
        return repository.findAllPersons();
    }

    @Override
    public Optional<Person> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public Optional<Person> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }
}
