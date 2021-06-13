package it.engineering.aleksandar.jovanov.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "professor")
public class ProfessorEntity implements MyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "firstname", nullable = false, length = 30)
	@Size(min = 3)
	private String firstname;
	@Column(name = "lastname", nullable = false, length = 30)
	@Size(min = 3)
	private String lastname;
	@Email
	@Size(max = 30)
	@Column(unique = true, nullable = true)
	private String email;
	@PrePersist
	@PreUpdate
	private void prepareData() {
		this.email = email == null ? null : email.toLowerCase();
	}
	@Column(name = "adress", nullable = true, length = 50)
	@Size(min = 3)
	private String adress;
	@ManyToOne
	@JoinColumn(name = "city_code", nullable = true)
	private CityEntity city;
	@Column(name = "phone", nullable = false, length = 15)
	@Size(min = 9)
	private String phone;
	@ManyToOne
	@JoinColumn(name = "title_id", nullable = true)
	private TitleEntity title;
	@Column(name = "reelection_date", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yy")
	private Date reelection_date;
	@ManyToMany(fetch = FetchType.LAZY,targetEntity = SubjectEntity.class)
	private List<SubjectEntity> subjects;
	public ProfessorEntity() {
		// TODO Auto-generated constructor stub
		subjects = new ArrayList<SubjectEntity>();
	}
	public ProfessorEntity(Long id, @Size(min = 3) String firstname, @Size(min = 3) String lastname,
			@Email @Size(max = 30) String email, @Size(min = 3) String adress, CityEntity city,
			@Size(min = 9) String phone, @Size(min = 9) Date reelection_date) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.phone = phone;
		this.reelection_date = reelection_date;
		subjects = new ArrayList<SubjectEntity>();
	}
	//GET SET HASHCODE EQUALS TOSTIRNG metode
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

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public TitleEntity getTitle() {
		return title;
	}

	public void setTitle(TitleEntity title) {
		this.title = title;
	}

	public Date getReelection_date() {
		return reelection_date;
	}

	public void setReelection_date(Date reelection_date) {
		this.reelection_date = reelection_date;
	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfessorEntity other = (ProfessorEntity) obj;
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (reelection_date == null) {
			if (other.reelection_date != null)
				return false;
		} else if (!reelection_date.equals(other.reelection_date))
			return false;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public List<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

}