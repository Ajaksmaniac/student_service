package it.engineering.aleksandar.jovanov.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.aleksandar.jovanov.entity.ExamEntity;

public class ExaminationPeriodDto implements MyDto {
	private Long id;
	private String name;
	private Date starting_period;
	private Date ending_period;
	private boolean active;
	private List<ExamDto> exams;
	public ExaminationPeriodDto() {
	exams=  new ArrayList<ExamDto>();
	}
	public ExaminationPeriodDto(Long id,
			String name,
			Date starting_period,
			Date ending_period,
			boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.starting_period = starting_period;
		this.ending_period = ending_period;
		this.active = active;
		exams=  new ArrayList<ExamDto>();
	}
	//GET SET HASHCODE EQUALS TOSTIRNG metode

	
	
	public List<ExamDto> getExams() {
		return exams;
	}


	public void setExams(List<ExamDto> exams) {
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
		ExaminationPeriodDto other = (ExaminationPeriodDto) obj;
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
