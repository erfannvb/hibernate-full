package nvb.dev.repository;

import nvb.dev.base.repository.BaseRepository;
import nvb.dev.model.Programmer;

import java.util.Optional;

public interface ProgrammerRepository extends BaseRepository<Long, Programmer> {

    Optional<Programmer> findByProgrammingLanguage(String programmingLanguage);

    Optional<Programmer> findByLevel(String level);

}
