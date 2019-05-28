package presenter;

import data.Cinema;
import data.Movie;
import data.PLACE_STATUS;
import data.Session;
import view.customView.SeatView;

import java.util.HashSet;

public interface IMVPContract {

    interface IMainScene extends IMVPView{
        void onMovieDataReady(Movie[] data);
        void onCinemaDataReady(Cinema[] data);
        void update();
    }

    interface IPreviewScene extends IMVPView{
        void onSessionDataReady(Session[] data);
        void setMovies(HashSet<String> data);
        void setCinemas(HashSet<String> data);
        void setHalls(HashSet<String> data);
        void setDates(HashSet<String> data);
        void setTimes(HashSet<String> data);
        void update();
    }

    interface IChooseScene extends IMVPView{
        void openBuyDialog(SeatView seat);
        void markPlace(int row, int column);
        void updatePlace(int row, int column, PLACE_STATUS place_status);
        void update(Session data);
        void setData(Session data);
        void showLocalError(String error);
    }

    interface ISignScene extends IMVPView{
        void showError(String error);
    }

}
