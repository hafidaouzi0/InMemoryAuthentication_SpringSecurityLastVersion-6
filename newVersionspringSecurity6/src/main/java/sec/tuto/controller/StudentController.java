package sec.tuto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sec.tuto.model.Student;
@RestController
public class StudentController {
	//should be globa
	List<Student> students=new ArrayList<>();

	@GetMapping("/students")
	public List<Student> listStudents(){
		
		
		Student s1=new Student();
		s1.setFirstName("hafida");
		s1.setId(20L);
		s1.setLastName("faouzi");
		Student s2=new Student();
		s2.setId(22L);
		s2.setFirstName("safia");
		s2.setLastName("faouzi");
		Student s3=new Student();
		s3.setId(23L);
		s3.setFirstName("abd elbar");
		s3.setLastName("faouzi");
		students.add(s1);
		students.add(s2);
		students.add(s3);
		
		return students;
	
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/students/saved")
	public Student createStudent(@RequestBody Student s) {
		students.add(s);
		return s;
		
	}

}
