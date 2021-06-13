package it.engineering.aleksandar.jovanov.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "examination_period")
public class ExaminationPeriodEntity implements MyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false, length = 30)
	@Size(min = 3)
	private String name;
	@Column(name = "starting_period", nullable = false)
	@Size(min = 9)
	private Date starting_period;
	@Column(name = "ending_period", nullable = false)
	@Size(min = 9)
	private Date ending_period;
	@Column(columnDefinition = "tinyint(1) default 0")
	private boolean active;
	@ManyToMany(fetch = FetchType.LAZY,targetEntity = ExamEntity.class)
	private List<ExamEntity> exams;
	public ExaminationPeriodEntity() {
		exams = new ArrayList<ExamEntity>();
	}
	public ExaminationPeriodEntity(Long id,
			@Size(min = 3) String name,
			@Size(min = 9) Date starting_period,
			@Size(min = 9) Date ending_period,
			boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.starting_period = starting_period;
		this.ending_period = ending_period;
		this.active = active;
		exams = new ArrayList<ExamEntity>();
	}
	//GET SET HASHCODE EQUALS TOSTIRNG metode
	
	public void addExam(ExamEntity exam) {
		this.exams.add(exam);
	}
	public List<ExamEntity> getExams() {
		return exams;
	}


	public void setExams(List<ExamEntity> exams) {
		this.exams = exams;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getStarting_period() {
		return starting_period;
	}


	public void setStarting_period(Date starting_period) {
		this.starting_period = starting_period;
	}


	public Date getEnding_period() {
		return ending_period;
	}


	public void setEnding_period(Date ending_period) {
		this.ending_period = ending_period;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((ending_period == null) ? 0 : ending_period.hashCode());
		result = prime * result + ((exams == null) ? 0 : exams.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((starting_period == null) ? 0 : starting_period.hashCode());
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
		ExaminationPeriodEntity other = (ExaminationPeriodEntity) obj;
		if (active != other.active)
			return false;
		if (ending_period == null) {
			if (other.ending_period != null)
				return false;
		} else if (!ending_period.equals(other.ending_period))
			return false;
		if (exams == null) {
			if (other.exams != null)
				return false;
		} else if (!exams.equals(other.exams))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (starting_period == null) {
			if (other.starting_period != null)
				return false;
		} else if (!starting_period.equals(other.starting_period))
			return false;
		return true;
	}



	
}
