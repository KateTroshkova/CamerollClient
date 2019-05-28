package presenter;

import data.Cinema;
import data.Movie;

public class Cache {
    private static Cache ourInstance = new Cache();

    private Movie movie;
    private Cinema cinema;

    public static Cache getInstance() {
        return ourInstance;
    }

    private Cache() {
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
}
