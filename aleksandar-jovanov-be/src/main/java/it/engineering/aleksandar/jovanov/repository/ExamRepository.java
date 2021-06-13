package it.engineering.aleksandar.jovanov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;

public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

	List<ExamEntity> findByExaminationPeriod(ExaminationPeriodEntity examinationPeriod);
	
	
	
}
