package br.edu.fa7.moviescrud.models;

/**
 * Created by alan on 9/5/15.
 */
public class Movie {

    private Long id;
    private String title;
    private String director;
    private String genre;

    public Movie() {};

    public Movie(String title, String director, String genre) {
        this.title = title;
        this.director = director;
        this.genre = genre;
    }

    public Movie(Long id, String title, String director, String genre) {
        this(title, director, genre);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
