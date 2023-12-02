package nvb.dev.repository;

import nvb.dev.base.repository.BaseRepository;
import nvb.dev.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends BaseRepository<Long, Student> {

    Optional<List<Student>> findAllStudents();

    Optional<Student> findByCourse(String course);

}
