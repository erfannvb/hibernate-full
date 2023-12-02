package nvb.dev.repository.impl;

import nvb.dev.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.model.Athlete;
import nvb.dev.repository.AthleteRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class AthleteRepositoryImpl extends BaseRepositoryImpl<Long, Athlete> implements AthleteRepository {

    protected final Session session;

    public AthleteRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<Athlete> getEntityClass() {
        return Athlete.class;
    }

    @Override
    public Optional<List<Athlete>> findAllAthletes() {
        String hql = "from Athlete";
        Query<Athlete> query = session.createQuery(hql, Athlete.class);
        return Optional.ofNullable(query.getResultList());
    }

    @Override
    public Optional<Athlete> findBySportField(String sportField) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Athlete> criteriaQuery = builder.createQuery(Athlete.class);

        Root<Athlete> athleteRoot = criteriaQuery.from(Athlete.class);
        criteriaQuery.where(builder.equal(athleteRoot.get("sportField"), sportField));

        TypedQuery<Athlete> typedQuery = session.createQuery(criteriaQuery);

        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}
