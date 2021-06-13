package it.engineering.aleksandar.jovanov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;


public interface SubjectService {
	Optional<SubjectDto> findById(Long id);
	List<SubjectDto> getAll();
	SubjectDto save(SubjectDto dto) throws MyEntityExistException;
	Optional<SubjectDto> update(SubjectDto dto);
	void delete(Long id) throws MyEntityNotPresentedException;
	Page<SubjectDto> findByPage(Pageable pageable);
}
