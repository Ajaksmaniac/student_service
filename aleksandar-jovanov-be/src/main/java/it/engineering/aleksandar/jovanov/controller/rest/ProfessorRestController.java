package it.engineering.aleksandar.jovanov.controller.rest;

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
import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.exception.InvalidEmailException;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.logging.Loggable;
import it.engineering.aleksandar.jovanov.service.ProfessorService;
import it.engineering.aleksandar.jovanov.service.SubjectService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/professor")
public class ProfessorRestController {
	
	private final ProfessorService professorService;
	@Autowired
	public ProfessorRestController(ProfessorService professorService) {
	
		this.professorService = professorService;
	}

	
	@Loggable
	@GetMapping("/{professorId}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long professorId) {
		Optional<ProfessorDto> professorDto=professorService.findById(professorId);
		if(professorDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(professorDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Professor with id " + professorId+" does not exist!");
		}
	}
	@Loggable
	@GetMapping
	public @ResponseBody ResponseEntity<List<ProfessorDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
	}
	@Loggable
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ProfessorDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.findByPage(pageable));
	}
	
	@Loggable
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ProfessorDto professorDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professorDto));
		} catch (Exception   e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod cuvanja entiteta: " + professorDto);
		}
	}
	@Loggable
	@PutMapping
	public @ResponseBody ResponseEntity<ProfessorDto> update(@RequestBody ProfessorDto subjectDto)  {
		Optional<ProfessorDto> subject;
		try {
			subject = professorService.update(subjectDto);
			if (subject.isPresent()) {
				System.out.println("Updated " + subject);
				return ResponseEntity.status(HttpStatus.OK).body(subject.get());
			}
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(subjectDto);
		
	}
	@Loggable
	@DeleteMapping("/{professorId}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "professorId") Long professorId) {
		try {
			professorService.delete(professorId);
			System.out.println("Deleted professor with id: "+ professorId );
			return ResponseEntity.status(HttpStatus.OK).body("Deleted subject with id:" + professorId);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
