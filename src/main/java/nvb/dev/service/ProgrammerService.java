package nvb.dev.service;

import nvb.dev.base.service.BaseService;
import nvb.dev.model.Programmer;

import java.util.List;
import java.util.Optional;

public interface ProgrammerService extends BaseService<Long, Programmer> {

    void addProgrammer(Programmer programmer);

    boolean validate(Programmer programmer);

    Optional<List<Programmer>> findAllProgrammers();

    Optional<Programmer> findByProgrammingLanguage(String programmingLanguage);

    Optional<Programmer> findByLevel(String level);

}
