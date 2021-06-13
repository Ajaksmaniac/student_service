package it.engineering.aleksandar.jovanov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import it.engineering.aleksandar.jovanov.dto.StudentDto;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;

public interface StudentService {
	Optional<StudentDto> findById(Long id);
	List<StudentDto> getAll();
	StudentDto save(StudentDto dto) throws MyEntityExistException, MyEntityNotPresentedException, Exception;
	Optional<StudentDto> update(StudentDto dto) throws MyEntityNotPresentedException,MyEntityExistException, Exception;
	void delete(Long id) throws MyEntityNotPresentedException;
	Page<StudentDto> findByPage(Pageable pageable);
	StudentDto findByIndex(Long indexYear,Long indexNumber);
}
