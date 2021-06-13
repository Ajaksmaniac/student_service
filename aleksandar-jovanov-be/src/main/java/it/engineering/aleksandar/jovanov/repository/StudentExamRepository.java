package it.engineering.aleksandar.jovanov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.engineering.aleksandar.jovanov.entity.StudentExamEntity;

public interface StudentExamRepository extends JpaRepository<StudentExamEntity, Long> {
	
	@Query("SELECT se FROM StudentExamEntity se WHERE se.exam.id = :exam AND se.student.id = :student")
	List<StudentExamEntity> findByExamAndStudent(@Param("exam") Long exam,@Param("student") Long student );
	
	@Query("SELECT se FROM StudentExamEntity se WHERE se.grade = 0")
	List<StudentExamEntity> findAllUngradedExams();

}
