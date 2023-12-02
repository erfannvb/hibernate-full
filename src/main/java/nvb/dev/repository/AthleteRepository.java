package nvb.dev.repository;

import nvb.dev.base.repository.BaseRepository;
import nvb.dev.model.Athlete;

import java.util.Optional;

public interface AthleteRepository extends BaseRepository<Long, Athlete> {

    Optional<Athlete> findBySportField(String sportField);

}
