package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.utils.Specialite;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {    	
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.ok(savedStudent);
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAllStudents() {
        studentService.deleteAllStudent();
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{studentId}/university/{universityId}")
    public ResponseEntity<Student> assignStudentToUniversity(
            @PathVariable UUID studentId,
            @PathVariable UUID universityId) {

        Student student = studentService.assignStudentToUniversity(studentId, universityId);
        return ResponseEntity.ok(student);
    }

}
