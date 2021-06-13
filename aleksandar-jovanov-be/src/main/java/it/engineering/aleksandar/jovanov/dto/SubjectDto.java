package it.engineering.aleksandar.jovanov.dto;

import it.engineering.aleksandar.jovanov.entity.Semester;

public class SubjectDto implements MyDto{

	private Long id;
	private String name;
	private String description;
	private int noOfEsp;
	private int yearOfStudy;
	private Semester semester;
	
	public SubjectDto() {
		// TODO Auto-generated constructor stub
	}

	public SubjectDto(Long id, String name, String description, int noOfEsp, int yearOfStudy, Semester semester) {
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
		SubjectDto other = (SubjectDto) obj;
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
