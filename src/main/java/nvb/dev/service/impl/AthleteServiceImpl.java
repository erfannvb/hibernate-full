package nvb.dev.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import nvb.dev.base.service.impl.BaseServiceImpl;
import nvb.dev.exception.ValidationException;
import nvb.dev.model.Athlete;
import nvb.dev.repository.AthleteRepository;
import nvb.dev.service.AthleteService;
import org.hibernate.Session;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AthleteServiceImpl extends BaseServiceImpl<Long, Athlete, AthleteRepository>
        implements AthleteService {

    protected final Session session;

    public AthleteServiceImpl(Session session, AthleteRepository repository) {
        super(session, repository);
        this.session = session;
    }

    @Override
    public void addAthlete(Athlete athlete) {
        session.getTransaction().begin();
        repository.save(athlete);
        session.getTransaction().commit();
    }

    @Override
    public boolean validate(Athlete athlete) {
        try {

            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory();

            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Athlete>> violationSet = validator.validate(athlete);

            if (!violationSet.isEmpty()) {
                for (ConstraintViolation<Athlete> violation : violationSet) {
                    System.out.println(violation.getMessage());
                }
                validatorFactory.close();
                return false;
            } else {
                addAthlete(athlete);
                return true;
            }

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<List<Athlete>> findAllAthletes() {
        return repository.findAllAthletes();
    }

    @Override
    public Optional<Athlete> findBySportField(String sportField) {
        return repository.findBySportField(sportField);
    }
}
