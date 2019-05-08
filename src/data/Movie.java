package data;

import javafx.scene.image.Image;
import model.RandomImageGenerator;

import java.io.Serializable;
import java.util.Objects;

/*
bd version
id - autoincrement integer
name - text
description - text
actors - text
year - integer
country - text
 */

/*
Связан с Сеансом
Пустой конструктор для десериализации json
 */

public class Movie implements Serializable, IRandomShow {

    private long id;
    private String name;
    private String description;
    private String actors;
    private String genre;
    private String country;

    public Movie(){

    }

    public Movie(String name, String description, String actors, String genre, String country){
        this.name=name;
        this.description=description;
        this.actors=actors;
        this.genre=genre;
        this.country=country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Image getImage() {
        return RandomImageGenerator.getInstance().getImage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(name, movie.name) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(actors, movie.actors) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(country, movie.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, actors, genre, country);
    }
}
