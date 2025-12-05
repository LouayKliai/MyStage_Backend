package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import src.universite.University;
import src.utils.Specialite;


public interface StudentService {
	
	Student createStudent(Student s);
	void deleteStudent(UUID id);	
	void deleteAllStudent();
	
	Optional<Student> getStudentById(UUID id);
    Optional<Student> getStudentByEmail(String email);    

    List<Student> getAllStudents();
    List<Student> getStudentsBySpecialite(Specialite specialite);
    List<Student> getStudentsByUniversityId(UUID university_id);

	
		

}
