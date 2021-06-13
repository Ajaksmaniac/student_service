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

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.dto.ExaminationPeriodDto;

import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.logging.Loggable;
import it.engineering.aleksandar.jovanov.service.ExaminationPeriodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/period")
public class ExaminationPeriodRestController {

	private final ExaminationPeriodService examinationPeriodService;
	
	
	@Autowired
	public ExaminationPeriodRestController(ExaminationPeriodService examinationPeriodService) {
		this.examinationPeriodService =examinationPeriodService;
	}
	
	@Loggable
	@GetMapping("/{examPeriodId}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long examPeriodId) {
		Optional<ExaminationPeriodDto> cityDto=examinationPeriodService.findById(examPeriodId);
		if(cityDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cityDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Exam period with code " + examPeriodId+" does not exist!");
		}
	}
	@Loggable
	@GetMapping
	public @ResponseBody ResponseEntity<List<ExaminationPeriodDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(examinationPeriodService.getAll());
	}
	@Loggable
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ExaminationPeriodDto dto) throws MyEntityNotPresentedException, Exception {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examinationPeriodService.save(dto));
		} catch (MyEntityExistException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod cuvanja entiteta: " + dto);
		}
	}
	@Loggable
	@PutMapping
	public @ResponseBody ResponseEntity<ExaminationPeriodDto> update(@RequestBody ExaminationPeriodDto dto) throws MyEntityNotPresentedException, MyEntityExistException, Exception {
		Optional<ExaminationPeriodDto> period= examinationPeriodService.update(dto);
		if (period.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(period.get());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}
	}
	@Loggable
	@DeleteMapping("/{examPeriodId}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "examPeriodId") Long examPeriodId) {
		try {
			examinationPeriodService.delete(examPeriodId);
			//System.out.println("Deleted professor with id: "+ professorId );
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Exam period with id:" + examPeriodId);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@Loggable
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ExaminationPeriodDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examinationPeriodService.findByPage(pageable));
	}
	@Loggable
	@GetMapping("/active")
	public @ResponseBody ResponseEntity<Object> findActivePeriod() {
		ExaminationPeriodDto period=examinationPeriodService.findActiveExaminationPeriod();
		if(period != null) {
			return ResponseEntity.status(HttpStatus.OK).body(period);
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No Active examination periods");
		}
	}
	
}
