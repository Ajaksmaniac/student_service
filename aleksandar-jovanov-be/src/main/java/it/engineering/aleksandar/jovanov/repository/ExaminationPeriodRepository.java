package it.engineering.aleksandar.jovanov.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;

public interface ExaminationPeriodRepository extends JpaRepository<ExaminationPeriodEntity, Long> {
	@Transactional
	  @Modifying
	@Query("UPDATE ExaminationPeriodEntity SET active = 0")
	void setAllToInActive();
	
	
	@Query("SELECT ep FROM ExaminationPeriodEntity ep WHERE ep.starting_period between :start AND :end ")
	List<ExaminationPeriodEntity> getByDatesBetween(@Param("start") Date start,@Param("end") Date end);
	
	

	
	@Query("SELECT ep FROM ExaminationPeriodEntity ep WHERE ep.starting_period between :start AND :end AND ep.id != :id")
	List<ExaminationPeriodEntity> getByDatesBetween(@Param("start") Date start,@Param("end") Date end,@Param("id")Long id);
	
	@Query("UPDATE ExaminationPeriodEntity ep SET ep.exams = :exams WHERE ep.id = :id")
	void updateExams(@Param("id") Long id,@Param("exams") List<ExamEntity> exams );
	
	@Query("SELECT ep FROM ExaminationPeriodEntity ep WHERE ep.active = 1")
	ExaminationPeriodEntity findActiveExamPeriod();
	
	
	
}
