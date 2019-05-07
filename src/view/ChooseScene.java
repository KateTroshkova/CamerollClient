package view;

import data.PLACE_STATUS;
import data.Place;
import data.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.Validation;
import presenter.IMVPContract;
import view.customView.SeatView;

import java.io.IOException;

public class ChooseScene extends SignableScene implements IMVPContract.IChooseScene{

    @FXML
    private AnchorPane hallLayout;

    private Session data;

    public ChooseScene(Session session){
        this.data=session;
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
    }

    @FXML
    private void initialize(){
        String pattern[]=data.getHall().getPlacePattern().split(";");
        int width=Integer.valueOf(pattern[0]);
        int height=Integer.valueOf(pattern[1]);
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
                view.setOnMouseClicked(event -> {
                    if (new Validation().inSystem()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Results:");
                        alert.setContentText("Connect to the database successfully!");

                        alert.showAndWait();
                    }
                });
                hallLayout.getChildren().add(view);
                count++;
            }
        }
    }

    @Override
    public void openBuyDialog() {

    }

    @Override
    public void markPlace() {

    }

    @Override
    public void updatePlace() {

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
