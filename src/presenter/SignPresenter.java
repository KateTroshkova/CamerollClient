package presenter;

import data.*;
import model.connection.Connection;
import model.connection.RemoteRead;
import request.SignInRequest;
import request.SignUpRequest;

public class SignPresenter extends BasePresenter<IMVPContract.ISignScene> implements RemoteRead.OnReaderListener{
    @Override
    public void viewIsReady() {

    }

    public void register(Registration registration){
        Connection.getInstance().addReadListener(this);
        Connection.getInstance().setSignController(getView());
        Connection.getInstance().send(new SignUpRequest(registration));
    }

    public void enter(Entrance entrance){
        Connection.getInstance().addReadListener(this);
        Connection.getInstance().setSignController(getView());
        Connection.getInstance().send(new SignInRequest(entrance));
    }

    @Override
    public void onGetMovie(Movie[] data) {

    }

    @Override
    public void onGetCinema(Cinema[] data) {

    }

    @Override
    public void onGetSession(Session[] data) {

    }


    @Override
    public void onUser(User user) {
        SystemState.setUser(user);
    }

    @Override
    public void onError(String error) {
        SystemState.setUser(null);
    }
}
