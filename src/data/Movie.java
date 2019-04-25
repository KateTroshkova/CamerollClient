package data;

import javafx.scene.image.Image;

import java.io.Serializable;

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
    private int year;
    private String country;

    public Movie(){

    }

    public Movie(String name, String description, String actors, int year, String country){
        this.name=name;
        this.description=description;
        this.actors=actors;
        this.year=year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Image getImage() {
        return null;
    }
}
