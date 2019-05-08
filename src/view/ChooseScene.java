package view;

import data.PLACE_STATUS;
import data.Place;
import data.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import model.Validation;
import presenter.ChooseScenePresenter;
import presenter.IMVPContract;
import view.customView.SeatView;

import java.io.IOException;
import java.util.Optional;

public class ChooseScene extends SignableScene implements IMVPContract.IChooseScene{

    @FXML
    private AnchorPane hallLayout;

    private Session data;

    private ChooseScenePresenter presenter;

    private SeatView[][] seats;

    public ChooseScene(Session session){
        this.data=session;
        presenter=new ChooseScenePresenter();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choose_screen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize();
        registerMenuAction();
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @FXML
    private void initialize(){
        String pattern[]=data.getHall().getPlacePattern().split(";");
        int width=Integer.valueOf(pattern[0]);
        int height=Integer.valueOf(pattern[1]);
        seats=new SeatView[height][width];
        int count=2;
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                Place place=null;
                switch(pattern[count]){
                    case "-2":{
                        place=new Place(j*80, i*80, PLACE_STATUS.STATUS_NOT_EXIST);
                        break;
                    }
                    case "-1":{
                        place=new Place(j*80, i*80, PLACE_STATUS.STATUS_TAKEN);
                        break;
                    }
                    case "0":{
                        place=new Place(j*80, i*80, PLACE_STATUS.STATUS_FREE);
                        break;
                    }
                    default:{
                        place=new Place(j*80, i*80, PLACE_STATUS.STATUS_BOOKED);
                        break;
                    }
                }
                SeatView view=new SeatView(place);
                view.setLayoutX(j*80);
                view.setLayoutY(i*80);
                seats[j][i]=view;
                if (place.isExist()) {
                    view.setOnMouseClicked(event -> {
                        presenter.seatClick(view);
                    });
                }
                hallLayout.getChildren().add(view);
                count++;
            }
        }
    }

    @Override
    public void openBuyDialog(SeatView seat) {
        Alert buyDialog=new Alert(Alert.AlertType.INFORMATION);
        buyDialog.setTitle("Your ticket");
        buyDialog.setContentText("Фильм: "+data.getMovie().getName()+"\n"+
                "Кинотеатр: "+data.getCinema().getName()+"\n"+
                "Зал "+data.getHall().getName()+"\n"+
        "Время: "+data.getDate()+" "+data.getTime()+"\n"+
                "Место: "+seat.getData().getTicketRow()+" ряд"+seat.getData().getTicketColumn()+" место"+"\n"+
        "Цена: "+data.getPrice());

        ButtonType buy = new ButtonType("Buy");
        ButtonType book = new ButtonType("Book");
        ButtonType cancel = new ButtonType("Cancel");
        buyDialog.getButtonTypes().clear();
        buyDialog.getButtonTypes().addAll(buy, book, cancel);
        if (!new Validation().canBuy()){
            buyDialog.getDialogPane().lookupButton(buy).setDisable(true);
        }
        Optional<ButtonType> option = buyDialog.showAndWait();
        if (option.get() == null) {
            presenter.cancelClick(seat);
        } else if (option.get() == buy) {
            presenter.buyClick(seat);
        } else if (option.get() == book) {
            presenter.bookClick(seat);
        } else if (option.get() == cancel) {
            presenter.cancelClick(seat);
        } else {
            presenter.cancelClick(seat);
        }
    }

    @Override
    public void markPlace(int row, int column) {
        seats[row][column].mark();
        System.out.println(seats[row][column].getData().getTicketRow()+" "+seats[row][column].getData().getTicketColumn());
    }

    @Override
    public void updatePlace(int row, int column, PLACE_STATUS place_status) {
        seats[row][column].updateState(place_status);
    }

    @Override
    public void cancelMark() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void openSignInDialog() {

    }

    @Override
    public void openSignUpDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void blockBuyButton() {

    }

    @Override
    public void enableBuyButton() {

    }
}
