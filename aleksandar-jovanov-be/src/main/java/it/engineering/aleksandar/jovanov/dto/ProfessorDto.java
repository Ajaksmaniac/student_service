package it.engineering.aleksandar.jovanov.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.entity.TitleEntity;

public class ProfessorDto implements MyDto {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String adress;
	private CityDto city;
	private String phone;
	private TitleDto title;
	private Date reelection_date;
	private List<SubjectDto> subjects;
	public ProfessorDto() {
		subjects = new ArrayList<SubjectDto>();
	}
	public ProfessorDto(Long id,
			String firstname,
			String lastname,
			String email,
			String adress,
			CityDto city,
			String phone,
			TitleDto title,
			Date reelection_date) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.phone = phone;
		this.title = title;
		this.reelection_date = reelection_date;
		subjects = new ArrayList<SubjectDto>();
	}
	//GET SET HASHCODE EQUALS TOSTIRNG metode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((reelection_date == null) ? 0 : reelection_date.hashCode());
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	
	

	public List<SubjectDto> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public TitleDto getTitle() {
		return title;
	}

	public void setTitle(TitleDto title) {
		this.title = title;
	}

	public Date getReelection_date() {
		return reelection_date;
	}

	public void setReelection_date(Date reelection_date) {
		this.reelection_date = reelection_date;
	}

	

	
	
}