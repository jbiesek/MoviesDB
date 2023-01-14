package pl.jbiesek.MoviesDB.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private Date birth_date;

    @Column(name = "country")
    private String country;

    @OneToMany(
            mappedBy = "director",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<DirectorReview> directorReviews = new ArrayList<>();

    @OneToMany(
            mappedBy = "director",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Movie> movies = new ArrayList<>();

    public Director(String name, String surname, Date birth_date, String country) {
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.country = country;
    }

    public Director(String name, String surname, Date birth_date, String country, List<DirectorReview> directorReviews, List<Movie> movies) {
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.country = country;
        this.directorReviews = directorReviews;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<DirectorReview> getDirectorReviews() {
        return directorReviews;
    }

    public void setDirectorReviews(List<DirectorReview> directorReviews) {
        this.directorReviews = directorReviews;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
