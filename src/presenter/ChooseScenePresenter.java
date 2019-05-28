package presenter;

import data.*;
import model.Validation;
import model.connection.Connection;
import model.connection.RemoteRead;
import request.BookRequest;
import request.BuyRequest;
import view.customView.SeatView;

public class ChooseScenePresenter extends BasePresenter<IMVPContract.IChooseScene> implements RemoteRead.OnReaderListener {
    @Override
    public void viewIsReady() {

    }

    public void seatClick(SeatView seat){
        if (new Validation().inSystem()) {
            getView().markPlace(seat.getData().getRow(), seat.getData().getColumn());
            getView().openBuyDialog(seat);
        }
        else{
            getView().showLocalError("You need to be in system to buy a ticket");
        }
    }

    public void buyClick(Session session, int row, int column){
        Connection.getInstance().setChooseController(getView());
        Connection.getInstance().send(new BuyRequest(SystemState.getUser(), session, row, column));
    }

    public void cancelClick(SeatView seat){
        getView().updatePlace(seat.getData().getRow(), seat.getData().getColumn(), PLACE_STATUS.STATUS_FREE);
    }

    public void bookClick(Session session, int row, int column){
        Connection.getInstance().setChooseController(getView());
        Connection.getInstance().send(new BookRequest(SystemState.getUser(), session, row, column));
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

    }

    @Override
    public void onError(String error) {

    }
}
