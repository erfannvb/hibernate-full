package nvb.dev.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import nvb.dev.base.service.impl.BaseServiceImpl;
import nvb.dev.exception.ValidationException;
import nvb.dev.model.Programmer;
import nvb.dev.repository.ProgrammerRepository;
import nvb.dev.service.ProgrammerService;
import org.hibernate.Session;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProgrammerServiceImpl extends BaseServiceImpl<Long, Programmer, ProgrammerRepository>
        implements ProgrammerService {

    protected final Session session;

    public ProgrammerServiceImpl(Session session, ProgrammerRepository repository) {
        super(session, repository);
        this.session = session;
    }

    @Override
    public void addProgrammer(Programmer programmer) {
        session.getTransaction().begin();
        repository.save(programmer);
        session.getTransaction().commit();
    }

    @Override
    public boolean validate(Programmer programmer) {
        try {

            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory();

            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<Programmer>> violationSet = validator.validate(programmer);
            if (!violationSet.isEmpty()) {
                for (ConstraintViolation<Programmer> violation : violationSet) {
                    System.out.println(violation.getMessage());
                }
                validatorFactory.close();
                return false;
            } else {
                addProgrammer(programmer);
                return true;
            }

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<List<Programmer>> findAllProgrammers() {
        return repository.findAllProgrammers();
    }

    @Override
    public Optional<Programmer> findByProgrammingLanguage(String programmingLanguage) {
        return repository.findByProgrammingLanguage(programmingLanguage);
    }

    @Override
    public Optional<Programmer> findByLevel(String level) {
        return repository.findByLevel(level);
    }
}
