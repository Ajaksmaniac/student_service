package it.engineering.aleksandar.jovanov.controller.rest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.engineering.aleksandar.jovanov.dto.ProfessorDto;
import it.engineering.aleksandar.jovanov.dto.StudentDto;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.logging.Loggable;
import it.engineering.aleksandar.jovanov.repository.StudentRepository;
import it.engineering.aleksandar.jovanov.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/student")
public class StudentRestController {
	
	private final StudentService studentService;

	
	@Autowired
	public StudentRestController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	@Loggable
	@GetMapping("/{studentId}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long studentId) {
		Optional<StudentDto> studentDto=studentService.findById(studentId);
		if(studentDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(studentDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Student with id " + studentId+" does not exist!");
		}
	}
	@Loggable
	@GetMapping("/index/{year}/{number}")
	public @ResponseBody ResponseEntity<Object> findByIndex(@PathVariable Long year,@PathVariable Long number) {
		StudentDto studentDto=studentService.findByIndex(year,number);
		if(studentDto != null) {
			System.out.println(studentDto);

			return ResponseEntity.status(HttpStatus.OK).body(studentDto);
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Student with index " + year+"/"+number+" does not exist!");
		}
	}
	@Loggable
	@GetMapping
	public @ResponseBody ResponseEntity<List<StudentDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
	}
	@Loggable
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<StudentDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.findByPage(pageable));
	}
	@Loggable
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody StudentDto studentDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(studentService.save(studentDto));
		} catch (Exception   e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@Loggable
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody StudentDto studentDto)  {
		
		Optional<StudentDto> student;
		try {
			student = studentService.update(studentDto);
			if (student.isPresent()) {
				System.out.println("Updated " + student);
				return ResponseEntity.status(HttpStatus.OK).body(student.get());
			}
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to Update student");
		
	}
	@Loggable
	@DeleteMapping("/{studentId}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "studentId") Long studentId) {
		try {
			studentService.delete(studentId);
			System.out.println("Deleted Student with id: "+ studentId );
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Student with id:" + studentId);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	

}
