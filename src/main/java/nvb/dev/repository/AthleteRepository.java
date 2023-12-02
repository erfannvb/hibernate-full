package nvb.dev.repository;

import nvb.dev.base.repository.BaseRepository;
import nvb.dev.model.Athlete;

import java.util.List;
import java.util.Optional;

public interface AthleteRepository extends BaseRepository<Long, Athlete> {

    Optional<List<Athlete>> findAllAthletes();

    Optional<Athlete> findBySportField(String sportField);

}
