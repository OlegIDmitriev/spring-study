package ru.raiffesien.jtaboot.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name = Singer.FIND_ALL,
                query = "select s from Singer s"),
		@NamedQuery(name = Singer.COUNT_ALL,
                query = "select count(s) from Singer s")
})
public class Singer implements Serializable {

	public static final String FIND_ALL="Singer.findAll";
	public static final String COUNT_ALL="Singer.countAll";

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION")
	private int version;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

    public Singer() {
    }

    public void setId(Long id) {
		this.id = id;
	}


	public Long getId() {
		return this.id;
	}

	public int getVersion() {
		return version;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String toString() {
		return "Singer - Id: " + id + ", First name: " + firstName
				+ ", Last name: " + lastName + ", Birthday: " + birthDate;
	}
}
