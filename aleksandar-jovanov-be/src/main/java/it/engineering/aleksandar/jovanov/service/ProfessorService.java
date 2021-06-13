package it.engineering.aleksandar.jovanov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.aleksandar.jovanov.dto.ProfessorDto;

import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;

public interface ProfessorService {
	Optional<ProfessorDto> findById(Long id);
	List<ProfessorDto> getAll();
	ProfessorDto save(ProfessorDto dto) throws MyEntityExistException, MyEntityNotPresentedException, Exception;
	Optional<ProfessorDto> update(ProfessorDto dto) throws MyEntityNotPresentedException,MyEntityExistException, Exception;
	void delete(Long id) throws MyEntityNotPresentedException;
	Page<ProfessorDto> findByPage(Pageable pageable);
}
