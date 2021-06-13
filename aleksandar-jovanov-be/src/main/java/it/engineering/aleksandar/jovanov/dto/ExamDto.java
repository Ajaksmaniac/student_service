package it.engineering.aleksandar.jovanov.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;
import it.engineering.aleksandar.jovanov.entity.StudentExamEntity;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;

public class ExamDto implements MyDto {
	private Long id;
	private ExaminationPeriodDto examinationPeriod;
	private SubjectDto subject;
	private ProfessorDto professor;
	private Date exam_date;
	List<StudentExamDto> students;
	public ExamDto() {
		students = new ArrayList<StudentExamDto>();
	}
	public ExamDto(Long id, ExaminationPeriodDto examinationPeriod,
			SubjectDto subject,ProfessorDto professor,Date exam_date) {
		super();
		this.id = id;
		this.examinationPeriod = examinationPeriod;
		this.subject = subject;
		this.professor = professor;
		this.exam_date = exam_date;
		students = new ArrayList<StudentExamDto>();
	}
	//GET SET HASHCODE EQUALS TOSTIRNG metode
	
	
	
	
	public List<StudentExamDto> getStudents() {
		return students;
	}

	public void setStudents(List<StudentExamDto> students) {
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExaminationPeriodDto getExaminationPeriod() {
		return examinationPeriod;
	}

	public void setExaminationPeriod(ExaminationPeriodDto examinationPeriod) {
		this.examinationPeriod = examinationPeriod;
	}

	public SubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}

	public ProfessorDto getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorDto professor) {
		this.professor = professor;
	}

	public Date getExam_date() {
		return exam_date;
	}

	public void setExam_date(Date exam_date) {
		this.exam_date = exam_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exam_date == null) ? 0 : exam_date.hashCode());
		result = prime * result + ((examinationPeriod == null) ? 0 : examinationPeriod.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		ExamDto other = (ExamDto) obj;
		if (exam_date == null) {
			if (other.exam_date != null)
				return false;
		} else if (!exam_date.equals(other.exam_date))
			return false;
		if (examinationPeriod == null) {
			if (other.examinationPeriod != null)
				return false;
		} else if (!examinationPeriod.equals(other.examinationPeriod))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExamDto [id=" + id + ", examinationPeriod=" + examinationPeriod + ", subject=" + subject
				+ ", professor=" + professor + ", exam_date=" + exam_date + ", students=" + students + "]";
	}

	
	
}
