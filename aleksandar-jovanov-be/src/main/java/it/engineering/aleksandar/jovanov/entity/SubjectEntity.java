package it.engineering.aleksandar.jovanov.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "subject")
public class SubjectEntity implements MyEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable = false, length = 30)
	@Size(min = 3)
	private String name;
		
	@Column(name="description",nullable = true, length = 200)
	private String description;
	
	@Column(name="noOfEsp",nullable = false, length = 1)
	private int noOfEsp;
	
	@Column(name="yearOfStudy",nullable = false, length = 1)
	private int yearOfStudy;
	
	@Enumerated(EnumType.STRING)
	@Column(name="semester",nullable = false)
	@Size(max = 10)
	private Semester semester;
	
	
	
	public SubjectEntity() {
		// TODO Auto-generated constructor stub
	}
	
	

	public SubjectEntity(Long id, @Size(min = 3) String name, String description, int noOfEsp, int yearOfStudy,
			Semester semester) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfEsp = noOfEsp;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoOfEsp() {
		return noOfEsp;
	}

	public void setNoOfEsp(int noOfEsp) {
		this.noOfEsp = noOfEsp;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + noOfEsp;
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + yearOfStudy;
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
		SubjectEntity other = (SubjectEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (noOfEsp != other.noOfEsp)
			return false;
		if (semester != other.semester)
			return false;
		if (yearOfStudy != other.yearOfStudy)
			return false;
		return true;
	}




	
	
	
	
}
