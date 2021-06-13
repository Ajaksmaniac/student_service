package it.engineering.aleksandar.jovanov.entity;


import java.util.ArrayList;

import java.util.List;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student",uniqueConstraints = {@UniqueConstraint(columnNames = {"index_year","index_number"})})

public class StudentEntity implements MyEntity{
	
	



	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="index_year")
	private Long indexYear;
	@Column(name="index_number")
	private Long indexNumber;


	
	@Column(nullable = false,length = 30,name = "firstname")
	
	private String firstName;
	
	@Column(nullable = false,length = 30,name = "lastname")
	
	private String lastName;
	
	@Email
	@Size(max = 30)
	@Column(unique = true, nullable = true)
	private String email;

	@Column(name = "adress", nullable = true, length = 50)

	private String adress;
	
	@ManyToOne
	@JoinColumn(name = "city_code", nullable = true)
	private CityEntity city;
	
	@Column(nullable = false, name="current_year")
	private int currentYearOfStudy;
	
	 @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
	    List<StudentExamEntity> exams;
	public StudentEntity() {
		// TODO Auto-generated constructor stub
		exams = new ArrayList<StudentExamEntity>();
	}
	
	void addExam(StudentExamEntity studentExamEntity) {
		this.exams.add(studentExamEntity);
		
	}
	
	void removeExam(StudentExamEntity studentExamEntity) {
		
	}
	public StudentEntity( Long indexYear, Long indexNumber,Long id, String firstName, String lastName, @Email @Size(max = 30) String email, String adress,
			CityEntity city, int currentYearOfStudy) {
		super();
		this.id = id;
		this.indexYear = indexYear;
		this.indexNumber = indexNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.currentYearOfStudy = currentYearOfStudy;
		exams = new ArrayList<StudentExamEntity>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public CityEntity getCity() {
		return city;
	}
	public void setCity(CityEntity city) {
		this.city = city;
	}
	public int getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}
	public void setCurrentYearOfStudy(int currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}
	public List<StudentExamEntity> getExams() {
		return exams;
	}
	public void setExams(List<StudentExamEntity> exams) {
		this.exams = exams;
	}
	
	
	public Long getIndexYear() {
		return indexYear;
	}
	public void setIndexYear(Long indexYear) {
		this.indexYear = indexYear;
	}
	public Long getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(Long indexNumber) {
		this.indexNumber = indexNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + currentYearOfStudy;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((exams == null) ? 0 : exams.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indexNumber == null) ? 0 : indexNumber.hashCode());
		result = prime * result + ((indexYear == null) ? 0 : indexYear.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		StudentEntity other = (StudentEntity) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (currentYearOfStudy != other.currentYearOfStudy)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (exams == null) {
			if (other.exams != null)
				return false;
		} else if (!exams.equals(other.exams))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indexNumber == null) {
			if (other.indexNumber != null)
				return false;
		} else if (!indexNumber.equals(other.indexNumber))
			return false;
		if (indexYear == null) {
			if (other.indexYear != null)
				return false;
		} else if (!indexYear.equals(other.indexYear))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	

	
	

	
	
}
