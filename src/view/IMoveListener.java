package view;

import data.Cinema;
import data.Movie;

public interface IMoveListener {

    void mainToPreview(Movie movie);
    void mainToPreview(Cinema cinema);
}
