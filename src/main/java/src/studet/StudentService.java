package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import src.DTO.Student.StudentLoginDTO;
import src.DTO.Student.StudentProfileDTO;
import src.DTO.Student.StudentRegisterDTO;
import src.DTO.Student.StudentResponseDTO;
import src.DTO.Student.StudentUpdateDTO;
import src.universite.University;
import src.utils.Specialite;


public interface StudentService {
	
	Student register(StudentRegisterDTO dto);
	StudentResponseDTO login(StudentLoginDTO dto);
	
	Optional<Student> getStudentById(UUID id);
    Optional<Student> getStudentByEmail(String email);    
    List<Student> getAllStudents();
    List<Student> getStudentsBySpecialite(Specialite specialite);
    List<Student> getStudentsByUniversityId(UUID university_id);
    StudentResponseDTO updateProfile(UUID studentId, StudentUpdateDTO dto);	
    Student assignStudentToUniversity(UUID studentId, UUID universityId);

/*
	Student updateSkills(UUID id, List<String> skills);
	void deleteStudent(UUID id);	
	void deleteAllStudent();
	*/

		

}
