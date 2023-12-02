package nvb.dev.service;

import nvb.dev.base.service.BaseService;
import nvb.dev.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService extends BaseService<Long, Student> {

    void addStudent(Student student);

    boolean validate(Student student);

    Optional<List<Student>> findAllStudents();

    Optional<Student> findByCourse(String course);

}
