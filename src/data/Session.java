package data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/*
bd version
id - autoincrement integer
movie - id связанной таблицы Movie
cinema - id связанной таблицы Cinema
hall - id связанной таблицы Hall
time - text
date - text
price - integer not null
 */

public class Session implements Serializable, Comparator<Session> {

    private long id;
    private Movie movie;
    private Cinema cinema;
    private Hall hall;
    private String time;
    private String date;
    private int price;

    public Session(){}

    public Session(Movie movie, Cinema cinema, Hall hall, String time, String date, int price) {
        this.movie = movie;
        this.cinema = cinema;
        this.hall = hall;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id &&
                price == session.price &&
                Objects.equals(movie.getName(), session.movie.getName()) &&
                Objects.equals(cinema.getName(), session.cinema.getName()) &&
                Objects.equals(hall.getName(), session.hall.getName()) &&
                Objects.equals(time, session.time) &&
                Objects.equals(date, session.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, movie, cinema, hall, time, date, price);
    }

    @Override
    public int compare(Session o1, Session o2) {
        return 0;
    }
}
