package pl.jbiesek.MoviesDB.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "duration")
    private int duration;

    @Column(name = "country")
    private String country;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonIgnoreProperties("movie")
    private List<MovieReview> movieReviews = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "director_id", nullable = false)
    @JsonIgnoreProperties("directorsList")
    private Director director;

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnoreProperties("movies")
    private Set<Actor> actors = new LinkedHashSet<>();

    public Movie(int id, String title, int year, int duration, String country, String description, Director director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.country = country;
        this.description = description;
        this.director = director;
    }

    public Movie(String title, int year, int duration, String country, String description, List<MovieReview> movieReviews, Director director, Set<Actor> actors) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.country = country;
        this.description = description;
        this.movieReviews = movieReviews;
        this.director = director;
        this.actors = actors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieReview> getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(List<MovieReview> movies) {
        this.movieReviews = movies;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
