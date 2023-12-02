package nvb.dev.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import nvb.dev.base.service.impl.BaseServiceImpl;
import nvb.dev.exception.ValidationException;
import nvb.dev.model.Student;
import nvb.dev.repository.StudentRepository;
import nvb.dev.service.StudentService;
import org.hibernate.Session;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StudentServiceImpl extends BaseServiceImpl<Long, Student, StudentRepository>
        implements StudentService {

    protected final Session session;

    public StudentServiceImpl(Session session, StudentRepository repository) {
        super(session, repository);
        this.session = session;
    }

    @Override
    public void addStudent(Student student) {
        session.getTransaction().begin();
        repository.save(student);
        session.getTransaction().commit();
    }

    @Override
    public boolean validate(Student student) {
        try {

            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory();

            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<Student>> violationSet = validator.validate(student);
            if (!violationSet.isEmpty()) {
                for (ConstraintViolation<Student> violation : violationSet) {
                    System.out.println(violation.getMessage());
                }
                validatorFactory.close();
                return false;
            } else {
                addStudent(student);
                return true;
            }

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<List<Student>> findAllStudents() {
        return repository.findAllStudents();
    }

    @Override
    public Optional<Student> findByCourse(String course) {
        return repository.findByCourse(course);
    }
}
