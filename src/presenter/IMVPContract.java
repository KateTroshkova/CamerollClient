package presenter;

import data.Cinema;
import data.Movie;
import data.Session;

import java.util.ArrayList;

public interface IMVPContract {

    interface IMainScene extends IMVPView{
        void onMovieDataReady(Movie[] data);
        void onCinemaDataReady(Cinema[] data);
    }

    interface IPreviewScene extends IMVPView{
        void onSessionDataReady(Session[] data);
        void setMovies(ArrayList<String> data);
        void setCinemas(ArrayList<String> data);
        void setHalls(ArrayList<String> data);
        void setDates(ArrayList<String> data);
        void setTimes(ArrayList<String> data);
    }

}
