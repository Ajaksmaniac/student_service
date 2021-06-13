package it.engineering.aleksandar.jovanov.controller.rest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.logging.Loggable;
import it.engineering.aleksandar.jovanov.service.CityService;
import it.engineering.aleksandar.jovanov.service.SubjectService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/subject")
public class SubjectRestController {

	private final SubjectService subjectService;
	@Autowired
	public SubjectRestController(SubjectService subjectService) {
	
		this.subjectService = subjectService;
	}
	@Loggable
	@GetMapping("/{subjectId}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long subjectId) {
		Optional<SubjectDto> subjectDto=subjectService.findById(subjectId);
		if(subjectDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(subjectDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Subject with id " + subjectId+" does not exist!");
		}
	}
	@Loggable
	@GetMapping
	public @ResponseBody ResponseEntity<List<SubjectDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAll());
	}
	@Loggable
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<SubjectDto>> getByPage(Pageable pageable) {
		System.out.println("Geting pageable subjects: " );
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.findByPage(pageable));
	}
	@Loggable
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody SubjectDto subjectDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.save(subjectDto));
		} catch (MyEntityExistException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod cuvanja entiteta: " + subjectDto);
		}
	}
	@Loggable
	@DeleteMapping("/{subjectId}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "subjectId") Long subjectId) {
		try {
			subjectService.delete(subjectId);
			System.out.println("Deleted subject with id: "+ subjectId );
			return ResponseEntity.status(HttpStatus.OK).body("Deleted subject with id:" + subjectId);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@Loggable
	@PutMapping
	public @ResponseBody ResponseEntity<SubjectDto> update(@RequestBody SubjectDto subjectDto) {
		Optional<SubjectDto> subject= subjectService.update(subjectDto);
		if (subject.isPresent()) {
			System.out.println("Updated " + subject);
			return ResponseEntity.status(HttpStatus.OK).body(subject.get());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(subjectDto);
		}
	}
	
	
	
}
