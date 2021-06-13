package it.engineering.aleksandar.jovanov.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import it.engineering.aleksandar.jovanov.logging.Loggable;

@Entity
@Table(name="student_exam")
public class StudentExamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentEntity student;
	@ManyToOne
	
	@JoinColumn(name = "exam_id")
	private ExamEntity	exam;
	
	@Column(name = "applied_at")
	private LocalDate appliedAt;
	
	@Column(name="grade",nullable = true)
	@Min(1)
	@Max(10)
	private int grade;
	
	public StudentExamEntity() {
		// TODO Auto-generated constructor stub
	}

	public StudentExamEntity(Long id, StudentEntity student, ExamEntity exam, LocalDate appliedAt, int grade) {
		super();
		this.id = id;
		this.student = student;
		this.exam = exam;
		this.appliedAt = appliedAt;
		this.grade = grade;
	}
	
	

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public ExamEntity getExam() {
		return exam;
	}

	public void setExam(ExamEntity exam) {
		this.exam = exam;
	}

	public LocalDate getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDate appliedAt) {
		this.appliedAt = appliedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appliedAt == null) ? 0 : appliedAt.hashCode());
		result = prime * result + ((exam == null) ? 0 : exam.hashCode());
		result = prime * result + grade;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentExamEntity other = (StudentExamEntity) obj;
		if (appliedAt == null) {
			if (other.appliedAt != null)
				return false;
		} else if (!appliedAt.equals(other.appliedAt))
			return false;
		if (exam == null) {
			if (other.exam != null)
				return false;
		} else if (!exam.equals(other.exam))
			return false;
		if (grade != other.grade)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	
	
	
}
