package pl.jbiesek.MoviesDB.Entities;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "date_joined")
    private Date date_joined;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonIgnoreProperties("user")
    private List<ActorReview> actorReviews = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonIgnoreProperties("user")
    private List<DirectorReview> directorReviews = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonIgnoreProperties("user")
    private List<MovieReview> movieReviews = new ArrayList<>();

    public User(String login, String password, Date date_joined) {
        this.login = login;
        this.password = password;
        this.date_joined = date_joined;
    }

    public User(String login, String password, Date date_joined, List<ActorReview> actorReviews, List<DirectorReview> directorReviews, List<MovieReview> movieReviews) {
        this.login = login;
        this.password = password;
        this.date_joined = date_joined;
        this.actorReviews = actorReviews;
        this.directorReviews = directorReviews;
        this.movieReviews = movieReviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(Date date_joined) {
        this.date_joined = date_joined;
    }

    public List<ActorReview> getActorReviews() {
        return actorReviews;
    }

    public void setActorReviews(List<ActorReview> actorReviews) {
        this.actorReviews = actorReviews;
    }

    public List<DirectorReview> getDirectorReviews() {
        return directorReviews;
    }

    public void setDirectorReviews(List<DirectorReview> directorReviews) {
        this.directorReviews = directorReviews;
    }

    public List<MovieReview> getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(List<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
    }
}
