package presenter;

import data.Cinema;
import data.Movie;
import data.Session;

import java.util.ArrayList;
import java.util.HashSet;

public interface IMVPContract {

    interface IMainScene extends IMVPView{
        void onMovieDataReady(Movie[] data);
        void onCinemaDataReady(Cinema[] data);
    }

    interface IPreviewScene extends IMVPView{
        void onSessionDataReady(Session[] data);
        void setMovies(HashSet<String> data);
        void setCinemas(HashSet<String> data);
        void setHalls(HashSet<String> data);
        void setDates(HashSet<String> data);
        void setTimes(HashSet<String> data);
    }

}
