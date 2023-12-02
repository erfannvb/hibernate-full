package nvb.dev.repository.impl;

import nvb.dev.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.model.Programmer;
import nvb.dev.repository.ProgrammerRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class ProgrammerRepositoryImpl extends BaseRepositoryImpl<Long, Programmer> implements ProgrammerRepository {

    protected final Session session;

    public ProgrammerRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<Programmer> getEntityClass() {
        return Programmer.class;
    }

    @Override
    public Optional<List<Programmer>> findAllProgrammers() {
        String hql = "from Programmer";
        Query<Programmer> query = session.createQuery(hql, Programmer.class);
        return Optional.ofNullable(query.getResultList());
    }

    @Override
    public Optional<Programmer> findByProgrammingLanguage(String programmingLanguage) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Programmer> criteriaQuery = builder.createQuery(Programmer.class);

        Root<Programmer> programmerRoot = criteriaQuery.from(Programmer.class);
        criteriaQuery.where(builder.equal(programmerRoot.get("programmingLanguage"), programmingLanguage));

        TypedQuery<Programmer> typedQuery = session.createQuery(criteriaQuery);

        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public Optional<Programmer> findByLevel(String level) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Programmer> criteriaQuery = builder.createQuery(Programmer.class);

        Root<Programmer> programmerRoot = criteriaQuery.from(Programmer.class);
        criteriaQuery.where(builder.equal(programmerRoot.get("level"), level));

        TypedQuery<Programmer> typedQuery = session.createQuery(criteriaQuery);

        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}
