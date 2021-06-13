package it.engineering.aleksandar.jovanov.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.StudentEntity;

public class StudentExamDto implements MyDto {

	
    Long id;
	
	
	private StudentDto student;
	
	private ExamDto	exam;
	

	private LocalDate appliedAt;
	
	private int grade;
	
	public StudentExamDto() {
		// TODO Auto-generated constructor stub
	}

	
	
	public StudentExamDto(Long id, StudentDto student, ExamDto exam, LocalDate appliedAt,int grade) {
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

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	public ExamDto getExam() {
		return exam;
	}

	public void setExam(ExamDto exam) {
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
		StudentExamDto other = (StudentExamDto) obj;
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



	@Override
	public String toString() {
		return "StudentExamDto [id=" + id + ", student=" + student + ", exam=" + exam + ", appliedAt=" + appliedAt
				+ ", grade=" + grade + "]";
	}



	
	
	
	
	
}
