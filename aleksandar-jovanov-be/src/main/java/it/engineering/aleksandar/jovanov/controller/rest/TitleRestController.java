package it.engineering.aleksandar.jovanov.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.dto.TitleDto;
import it.engineering.aleksandar.jovanov.logging.Loggable;
import it.engineering.aleksandar.jovanov.service.TitleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/title")
public class TitleRestController {

	private final TitleService titleService;
	
	
	
	public TitleRestController(TitleService titleService) {
		super();
		this.titleService = titleService;
	}


	@Loggable
	@GetMapping
	public @ResponseBody ResponseEntity<List<TitleDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(titleService.getAll());
	}
}
