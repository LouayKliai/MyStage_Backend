package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import src.universite.University;
import src.universite.UniversityRepository;
import src.utils.Specialite;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepo;
	private final UniversityRepository universityRepo;

	 public StudentServiceImpl(StudentRepository studentRepo,
             UniversityRepository universityRepo) {
			this.studentRepo = studentRepo;
			this.universityRepo = universityRepo;
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
    	 List<Student> students = studentRepo.findAll();    	
    	    students.forEach(s -> s.getUniversity()); // force le chargement lazy
    	    return students;        
    }

    @Override
    public Optional<Student> getStudentById(UUID id) {
    	Optional<Student> student = studentRepo.findById(id);
        student.ifPresent(s -> s.getUniversity()); // force le chargement lazy
        return student;        
    }

    @Override
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepo.findByEmail(email);
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
    	if(!studentRepo.existsById(id)) {
    	    throw new EntityNotFoundException("Student introuvable");
    	}
    	studentRepo.deleteById(id);

    }


	@Override
	public void deleteAllStudent() {
		studentRepo.deleteAll();
		
	}
	public Student assignStudentToUniversity(UUID studentId, UUID universityId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student introuvable"));

        University university = universityRepo.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University introuvable"));

        university.addStudent(student); 
        student.setUniversity(university);
        student.getUniversity();
        
        return studentRepo.save(student);
    }


}
