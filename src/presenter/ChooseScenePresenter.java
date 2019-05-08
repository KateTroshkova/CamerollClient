package presenter;

import data.PLACE_STATUS;
import model.Validation;
import view.customView.SeatView;

public class ChooseScenePresenter extends BasePresenter<IMVPContract.IChooseScene> {
    @Override
    public void viewIsReady() {

    }

    public void seatClick(SeatView seat){
        if (new Validation().inSystem()) {
            getView().markPlace(seat.getData().getRow(), seat.getData().getColumn());
            getView().openBuyDialog(seat);
        }
        else{
            getView().showError("You need to be in system to buy a ticket");
        }
    }

    public void buyClick(SeatView seat){
        getView().updatePlace(seat.getData().getRow(), seat.getData().getColumn(), PLACE_STATUS.STATUS_TAKEN);
    }

    public void cancelClick(SeatView seat){
        getView().updatePlace(seat.getData().getRow(), seat.getData().getColumn(), PLACE_STATUS.STATUS_FREE);
    }

    public void bookClick(SeatView seat){
        getView().updatePlace(seat.getData().getRow(), seat.getData().getColumn(), PLACE_STATUS.STATUS_BOOKED);
    }

    public void signInClick(){

    }

    public void signUpClick(){

    }
}
