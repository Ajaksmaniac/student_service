package it.engineering.aleksandar.jovanov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.aleksandar.jovanov.dto.ExamDto;
import it.engineering.aleksandar.jovanov.dto.StudentExamDto;
import it.engineering.aleksandar.jovanov.exception.ExamAlreadyExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;


public interface ExamService {
	Optional<ExamDto> findById(Long id);
	List<ExamDto> getAll();
	ExamDto save(ExamDto dto) throws MyEntityNotPresentedException, MyEntityExistException, ExamAlreadyExistException, Exception;
	Optional<ExamDto> update(ExamDto dto) throws MyEntityNotPresentedException, MyEntityExistException, ExamAlreadyExistException;
	void delete(Long id) throws MyEntityNotPresentedException;
	Page<ExamDto> findByPage(Pageable pageable);
	List<ExamDto> findByExaminationPeriod(Long id);
	ExamDto applyStudentForExam(StudentExamDto studentExamDto) throws MyEntityNotPresentedException, Exception;
	Page<StudentExamDto> registeredExamsfindByPage(Pageable pageable);
	Optional<StudentExamDto> findStudentExamById(Long id);
	StudentExamDto updateStudentExam(StudentExamDto studentExamDto) throws Exception;
}
