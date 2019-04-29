package presenter;

import data.Movie;
import data.Session;

public class PreviewScenePresenter extends BasePresenter<IMVPContract.IPreviewScene> {

    @Override
    public void viewIsReady() {
        Session sessions[]=new Session[3];
        sessions[0]=new Session();
        sessions[1]=new Session();
        sessions[2]=new Session();
        sessions[0].setMovie(new Movie("The Godfather", "", "", 1972, ""));
        sessions[1].setMovie(new Movie("The Matrix", "", "", 1999, ""));
        sessions[2].setMovie(new Movie("The Shawshank Redemption", "", "", 1994, ""));
        getView().onSessionDataReady(sessions);
    }

    public void onFilterClick(String movie, String cinema, String hall, String time, String date){

    }
}
