package it.engineering.aleksandar.jovanov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.aleksandar.jovanov.dto.ExaminationPeriodDto;

import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;

public interface ExaminationPeriodService {
	Optional<ExaminationPeriodDto> findById(Long id);
	List<ExaminationPeriodDto> getAll();
	ExaminationPeriodDto save(ExaminationPeriodDto dto) throws MyEntityExistException, MyEntityNotPresentedException, Exception;
	Optional<ExaminationPeriodDto> update(ExaminationPeriodDto dto) throws MyEntityNotPresentedException,MyEntityExistException, Exception;
	void delete(Long id) throws MyEntityNotPresentedException;
	Page<ExaminationPeriodDto> findByPage(Pageable pageable);
	
	ExaminationPeriodDto findActiveExaminationPeriod();
}
