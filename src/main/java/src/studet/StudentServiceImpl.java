package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepo;
	
	

	public StudentServiceImpl(StudentRepository studentRepo) {
		this.studentRepo=studentRepo;
	}

	@Override
	public void addStudent(Student s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Student> getStudentById(UUID id) {		
		return studentRepo.getStudentById(id);
	}

}
