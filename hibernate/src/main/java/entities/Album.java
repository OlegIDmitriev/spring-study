package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "album")
public class Album implements Serializable {
    private Long id;
    private String title;
    private Date releaseDate;
    private int version;

    private Singer singer;

    public Album() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Version
    @Column(name = "version")
    public int getVersion() {
        return version;
    }

    @ManyToOne
    @JoinColumn(name = "singer_id")
    public Singer getSinger() {
        return singer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", version=" + version +
                ", singer=" + singer +
                '}';
    }
}
