package pl.jbiesek.MoviesDB.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

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
            mappedBy = "actor",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<ActorReview> actorReviews = new ArrayList<>();

    @OneToMany(
            mappedBy = "actor",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<ActorMovie> actorMovies = new ArrayList<>();


    public Actor(String name, String surname, Date birth_date, String country) {
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.country = country;
    }


    public Actor(String name, String surname, Date birth_date, String country, List<ActorReview> actorReviews, List<ActorMovie> actorMovies) {
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.country = country;
        this.actorReviews = actorReviews;
        this.actorMovies = actorMovies;
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

    public List<ActorReview> getActorReviews() {
        return actorReviews;
    }

    public void setActorReviews(List<ActorReview> actorReviews) {
        this.actorReviews = actorReviews;
    }

    public List<ActorMovie> getActorMovies() {
        return actorMovies;
    }

    public void setActorMovies(List<ActorMovie> actorMovies) {
        this.actorMovies = actorMovies;
    }
}
