package nvb.dev.repository.impl;

import nvb.dev.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.model.Student;
import nvb.dev.repository.StudentRepository;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Long, Student> implements StudentRepository {

    protected final Session session;

    public StudentRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Optional<Student> findByCourse(String course) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);

        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        criteriaQuery.where(builder.equal(studentRoot.get("course"), course));

        TypedQuery<Student> typedQuery = session.createQuery(criteriaQuery);

        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}
