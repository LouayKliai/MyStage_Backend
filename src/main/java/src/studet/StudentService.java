package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface StudentService {
	void addStudent(Student s);
	void deleteStudent(int id);
	List<Student> getStudents();
	Optional<Student> getStudentById(UUID id);
		

}
