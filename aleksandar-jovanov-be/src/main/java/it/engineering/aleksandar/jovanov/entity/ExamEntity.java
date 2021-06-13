package it.engineering.aleksandar.jovanov.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="exam")
public class ExamEntity implements MyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "examination_period_id", nullable = false)
	private ExaminationPeriodEntity examinationPeriod;
	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private SubjectEntity subject;
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private ProfessorEntity professor;
	@Column(name = "exam_date", nullable = false)
	private Date exam_date;
	 @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL,orphanRemoval = true)
	    List<StudentExamEntity> students;
	public ExamEntity() {
		students = new ArrayList<StudentExamEntity>();
	}
	public ExamEntity(Long id, ExaminationPeriodEntity examinationPeriod,
			SubjectEntity subject,ProfessorEntity professor,
			@Size(min = 9) Date exam_date) {
		super();
		this.id = id;
		this.examinationPeriod = examinationPeriod;
		this.subject = subject;
		this.professor = professor;
		this.exam_date = exam_date;
		students = new ArrayList<StudentExamEntity>();
	}
	//GET SET HASHCODE EQUALS TOSTIRNG metode

	
	
	public List<StudentExamEntity> getStudents() {
		return students;
	}


	public void setStudents(List<StudentExamEntity> students) {
		this.students = students;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ExaminationPeriodEntity getExaminationPeriod() {
		return examinationPeriod;
	}


	public void setExaminationPeriod(ExaminationPeriodEntity examinationPeriod) {
		this.examinationPeriod = examinationPeriod;
	}


	public SubjectEntity getSubject() {
		return subject;
	}


	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}


	public ProfessorEntity getProfessor() {
		return professor;
	}


	public void setProfessor(ProfessorEntity professor) {
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
		ExamEntity other = (ExamEntity) obj;
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


	
	
}
