package view;

import data.PLACE_STATUS;
import data.Place;
import data.Session;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import view.customView.SeatView;

import java.io.IOException;

public class ChooseScene extends BorderPane{

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
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Test Connection");
                    alert.setHeaderText("Results:");
                    alert.setContentText("Connect to the database successfully!");

                    alert.showAndWait();
                });
                hallLayout.getChildren().add(view);
                count++;
            }
        }
    }
}
