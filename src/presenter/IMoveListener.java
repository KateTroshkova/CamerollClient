package presenter;

import data.Cinema;
import data.Movie;
import data.Session;

public interface IMoveListener {

    void mainToPreview(Movie movie);
    void mainToPreview(Cinema cinema);
    void previewToChoose(Session session);
    void chooseToPreview();
    void previewToMain();
}
