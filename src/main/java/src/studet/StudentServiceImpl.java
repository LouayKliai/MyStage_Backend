package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import src.DTO.Student.StudentLoginDTO;
import src.DTO.Student.StudentProfileDTO;
import src.DTO.Student.StudentRegisterDTO;
import src.DTO.Student.StudentResponseDTO;
import src.DTO.Student.StudentUpdateDTO;
import src.universite.University;
import src.universite.UniversityRepository;
import src.utils.ROLE;
import src.utils.Specialite;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepo;
	private final UniversityRepository universityRepo;
	private final PasswordEncoder passwordEncoder;


	 public StudentServiceImpl(StudentRepository studentRepo,
             UniversityRepository universityRepo,PasswordEncoder passEnc) {
			this.studentRepo = studentRepo;
			this.universityRepo = universityRepo;
			this.passwordEncoder=passEnc;
	 }
	
	 @Override
	 public Student register(StudentRegisterDTO dto) {
		    Student student = new Student();
		    student.setFullname(dto.getFullname());
		    student.setEmail(dto.getEmail());		    
		    student.setPassword(passwordEncoder.encode(dto.getPassword()));
		    student.setRole(ROLE.ETUDIANT);
		    // skills vide automatiquement
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
/*
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
	@Override
	public Student updateSkills(UUID id, List<String> skills) {

	    Student student = studentRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Student not found"));

	    student.setSkills(skills);
	    return studentRepo.save(student);
	}*/
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

	
	@Override
	public StudentResponseDTO login(StudentLoginDTO dto) {

	    Student student = studentRepo.findByEmail(dto.getEmail())
	        .orElseThrow(() -> new RuntimeException("Email incorrect"));

	    // comparaison password clair ↔ hashé
	    if (!passwordEncoder.matches(dto.getPassword(), student.getPassword())) {
	        throw new RuntimeException("Mot de passe incorrect");
	    }

	    return new StudentResponseDTO(
	        student.getId(),
	        student.getFullname(),
	        student.getEmail(),
	        student.getSkills(),
	        student.getNiveau(),
	        student.getSpecialite(),
	        student.getBio()
	    );
	}

	@Override
	@Transactional
	public StudentResponseDTO updateProfile(UUID studentId, StudentUpdateDTO dto) {

	    Student student = studentRepo.findById(studentId)
	        .orElseThrow(() -> new EntityNotFoundException("Student not found"));

	    if(dto.getFullname() != null) student.setFullname(dto.getFullname());
	    if(dto.getEmail() != null) student.setEmail(dto.getEmail());
	    if(dto.getNiveau() != null) student.setNiveau(dto.getNiveau());
	    if(dto.getSpecialite() != null) student.setSpecialite(dto.getSpecialite());
	    if(dto.getBio() != null) student.setBio(dto.getBio());
	    if(dto.getSkills() != null) student.setSkills(dto.getSkills());

	    return new StudentResponseDTO(
	        student.getId(),
	        student.getFullname(),
	        student.getEmail(),
	        student.getSkills(),
	        student.getNiveau(),
	        student.getSpecialite(),
	        student.getBio()
	    );
	}




}
