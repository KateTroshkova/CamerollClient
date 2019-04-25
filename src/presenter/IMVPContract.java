package presenter;

import data.Cinema;
import data.Movie;

public interface IMVPContract {

    interface IMainScene extends IMVPView{
        void onMovieDataReady(Movie[] data);
        void onCinemaDataReady(Cinema[] data);
    }

}
