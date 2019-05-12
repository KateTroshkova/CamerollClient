package request;

import data.Cinema;
import data.Movie;

public class GetSessionRequest implements Request {

    private Cinema cinema;
    private Movie movie;

    public GetSessionRequest(Cinema cinema) {
        this.cinema = cinema;
    }

    public GetSessionRequest(Movie movie) {
        this.movie = movie;
    }

    public GetSessionRequest(){

    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
