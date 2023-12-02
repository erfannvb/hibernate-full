package nvb.dev.service;

import nvb.dev.base.service.BaseService;
import nvb.dev.model.Athlete;

import java.util.List;
import java.util.Optional;

public interface AthleteService extends BaseService<Long, Athlete> {

    void addAthlete(Athlete athlete);

    boolean validate(Athlete athlete);

    Optional<List<Athlete>> findAllAthletes();

    Optional<Athlete> findBySportField(String sportField);

}
