package nvb.dev.repository.impl;

import nvb.dev.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.model.Person;
import nvb.dev.repository.PersonRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Long, Person> implements PersonRepository {

    protected final Session session;

    public PersonRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<Person> getEntityClass() {
        return Person.class;
    }

    @Override
    public Optional<List<Person>> findAllPersons() {
        String hql = "from Person";
        Query<Person> query = session.createQuery(hql, Person.class);
        return Optional.ofNullable(query.getResultList());
    }

    @Override
    public Optional<Person> findByFirstName(String firstName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);
        criteriaQuery.where(builder.equal(personRoot.get("firstName"), firstName));

        TypedQuery<Person> personTypedQuery = session.createQuery(criteriaQuery);

        return Optional.ofNullable(personTypedQuery.getSingleResult());
    }

    @Override
    public Optional<Person> findByLastName(String lastName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);
        criteriaQuery.where(builder.equal(personRoot.get("lastName"), lastName));

        TypedQuery<Person> personTypedQuery = session.createQuery(criteriaQuery);

        return Optional.ofNullable(personTypedQuery.getSingleResult());
    }
}
