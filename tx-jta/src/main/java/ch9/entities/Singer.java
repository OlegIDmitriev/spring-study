package ch9.entities;

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

	@OneToMany(mappedBy = "singer", cascade=CascadeType.ALL,
			orphanRemoval=true)
	private Set<Album> albums = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "SINGER_ID"),
			inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
	private Set<Instrument> instruments = new HashSet<>();

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

	public Set<Album> getAlbums() {
		return albums;
	}


	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean addAbum(Album album) {
		album.setSinger(this);
		return getAlbums().add(album);
	}

	public void removeAlbum(Album album) {
		getAlbums().remove(album);
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}

	public boolean addInstrument(Instrument instrument) {
		return getInstruments().add(instrument);
	}

	public String toString() {
		return "Singer - Id: " + id + ", First name: " + firstName
				+ ", Last name: " + lastName + ", Birthday: " + birthDate;
	}
}
