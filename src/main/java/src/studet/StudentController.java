package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import src.DTO.Student.StudentLoginDTO;
import src.DTO.Student.StudentProfileDTO;
import src.DTO.Student.StudentRegisterDTO;
import src.DTO.Student.StudentResponseDTO;
import src.DTO.Student.StudentUpdateDTO;
import src.utils.Specialite;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/login")
    public ResponseEntity<StudentResponseDTO> login(
            @RequestBody StudentLoginDTO dto) {

        StudentResponseDTO student = studentService.login(dto);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/register")
    public ResponseEntity<Student> createStudent(@RequestBody StudentRegisterDTO dto) {    	
        Student savedStudent = studentService.register(dto);
        return ResponseEntity.ok(savedStudent);
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable UUID id) {

        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        StudentResponseDTO response = new StudentResponseDTO(
            student.getId(),
            student.getFullname(),
            student.getEmail(),
            student.getSkills(),
            student.getNiveau(),
            student.getSpecialite(),
            student.getBio()
            
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        Optional<Student> student = studentService.getStudentByEmail(email);
        return student.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialite/{specialite}")
    public List<Student> getStudentsBySpecialite(@PathVariable Specialite specialite) {
        return studentService.getStudentsBySpecialite(specialite);
    }

    @GetMapping("/university/{universityId}")
    public List<Student> getStudentsByUniversity(@PathVariable UUID universityId) {      
        return studentService.getStudentsByUniversityId(universityId);
    }

   
    @PutMapping("/{studentId}/university/{universityId}")
    public ResponseEntity<Student> assignStudentToUniversity(
            @PathVariable UUID studentId,
            @PathVariable UUID universityId) {

        Student student = studentService.assignStudentToUniversity(studentId, universityId);
        return ResponseEntity.ok(student);
    }
    
    
   
    @PutMapping("/{id}/profile")
    public ResponseEntity<StudentResponseDTO> updateProfile(
            @PathVariable UUID id,
            @RequestBody StudentUpdateDTO dto) {

        return ResponseEntity.ok(
            studentService.updateProfile(id, dto)
        );
    }

    
       
/*
    @PutMapping("/{id}/skills")
    public ResponseEntity<Student> updateSkills(
    		@PathVariable UUID id,
    		@RequestBody List<String> skills) {
    	
    	Student student = studentService.updateSkills(id, skills);
    	return ResponseEntity.ok(student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAllStudents() {
        studentService.deleteAllStudent();
        return ResponseEntity.noContent().build();
    }*/
}
