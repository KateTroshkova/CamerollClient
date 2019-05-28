package presenter;

import data.Cinema;
import data.Movie;
import data.Session;
import data.User;
import model.connection.Connection;
import model.connection.RemoteRead;
import request.GetCinemasRequest;
import request.GetMoviesRequest;

public class MainScenePresenter extends BasePresenter<IMVPContract.IMainScene> implements RemoteRead.OnReaderListener {

    @Override
    public void viewIsReady() {
        Connection.getInstance().addReadListener(this);
        Connection.getInstance().setMainController(getView());
        Connection.getInstance().send(new GetMoviesRequest());
        Connection.getInstance().send(new GetCinemasRequest());
    }

    @Override
    public void onGetMovie(Movie[] data) {
        getView().onMovieDataReady(data);
    }

    @Override
    public void onGetCinema(Cinema[] data) {
        getView().onCinemaDataReady(data);
    }

    @Override
    public void onGetSession(Session[] data) {

    }


    @Override
    public void onUser(User user) {

    }

    @Override
    public void onError(String error) {

    }
}
