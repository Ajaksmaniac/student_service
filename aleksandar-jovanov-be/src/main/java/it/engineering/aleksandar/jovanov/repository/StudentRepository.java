package it.engineering.aleksandar.jovanov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;
import it.engineering.aleksandar.jovanov.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	
	@Query("SELECT s FROM StudentEntity s WHERE s.indexNumber = :number AND s.indexYear = :year")
	StudentEntity FindByIndex(@Param("number") Long number,@Param("year") Long year );
}
