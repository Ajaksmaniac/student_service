package it.engineering.aleksandar.jovanov.controller.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import it.engineering.aleksandar.jovanov.dto.ExamDto;
import it.engineering.aleksandar.jovanov.dto.ProfessorDto;
import it.engineering.aleksandar.jovanov.dto.StudentExamDto;
import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.logging.Loggable;
import it.engineering.aleksandar.jovanov.service.ExamService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/exam")
public class ExamRestController {
	
	private ExamService examService;

	@Autowired
	public ExamRestController(ExamService examService) {
		super();
		this.examService = examService;
	}
	@Loggable
	@GetMapping("/{examId}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long examId) {
		Optional<ExamDto> examDto=examService.findById(examId);
		if(examDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Exam with id " + examId+" does not exist!");
		}
	}
	
	@Loggable
	@GetMapping
	public @ResponseBody ResponseEntity<List<ExamDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(examService.getAll());
	}
	@Loggable
	@GetMapping("/period/{periodId}")
	public @ResponseBody ResponseEntity<List<ExamDto>> getByExamPeriod(@PathVariable Long periodId) {
		return ResponseEntity.status(HttpStatus.OK).body(examService.findByExaminationPeriod(periodId));
	}
	@Loggable
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ExamDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examService.findByPage(pageable));
	}
	
	@Loggable
	@GetMapping("/registered/page")
	public @ResponseBody ResponseEntity<Page<StudentExamDto>> allRegisteredgetByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examService.registeredExamsfindByPage(pageable));
	}
	@Loggable
	@GetMapping("/registered/{studentExamId}")
	public @ResponseBody ResponseEntity<Object> getById(@PathVariable Long studentExamId) {
		Optional<StudentExamDto> studentExamDto= examService.findStudentExamById(studentExamId);
		if(studentExamDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(studentExamDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Student exam with id " + studentExamId+" does not exist!");
		}
	
	}
	@Loggable
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ExamDto examDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examService.save(examDto));
		} catch (Exception   e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@Loggable
	@PostMapping("/register")
	public @ResponseBody ResponseEntity<Object> save(@RequestBody StudentExamDto studentExamDto) {
		try {
			ExamDto dto = examService.applyStudentForExam(studentExamDto);
			
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} catch (Exception   e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@Loggable
	@PutMapping("/register")
	public @ResponseBody ResponseEntity<Object> updateStudentExam(@RequestBody StudentExamDto studentExamDto) {
		try {
			StudentExamDto dto = examService.updateStudentExam(studentExamDto);
			
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} catch (Exception   e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@Loggable
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody ExamDto examDto)  {
		Optional<ExamDto> exam;
		try {
			exam = examService.update(examDto);
			if (exam.isPresent()) {
				System.out.println("Updated " + exam);
				return ResponseEntity.status(HttpStatus.OK).body(exam.get());
			}
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(examDto);
		
	}
	@Loggable
	@DeleteMapping("/{examId}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "examId") Long examId) {
		try {
			examService.delete(examId);
			System.out.println("Deleted Exam with id: "+ examId );
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Exam with id:" + examId);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
