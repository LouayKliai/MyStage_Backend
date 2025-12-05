package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import src.universite.University;
import src.utils.Specialite;

@Service
public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepo;
	
	

	public StudentServiceImpl(StudentRepository studentRepo) {
		this.studentRepo=studentRepo;
	}


    @Override
    public Student createStudent(Student student) {
        if(student.getEmail() == null || student.getNom() == null) {
            throw new IllegalArgumentException("Nom et Email sont obligatoires !");
        }
        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> getStudentById(UUID id) {
        return studentRepo.findById(id);
    }

    @Override
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepo.findStudentByEmail(email);
    }

    @Override
    public List<Student> getStudentsBySpecialite(Specialite specialite) {
        return studentRepo.findBySpecialite(specialite);
    }

    @Override
    public List<Student> getStudentsByUniversityId(UUID university_id) {
        return studentRepo.findByUniversityId(university_id);
    }

    @Override
    public void deleteStudent(UUID id) {
    	studentRepo.deleteById(id);
    }


	@Override
	public void deleteAllStudent() {
		studentRepo.deleteAll();
		
	}


}
