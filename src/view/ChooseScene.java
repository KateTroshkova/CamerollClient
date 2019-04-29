package view;

import data.PLACE_STATUS;
import data.Place;
import data.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import view.customView.SeatView;

import java.io.IOException;

public class ChooseScene extends BorderPane{

    @FXML
    private AnchorPane hallLayout;

    private Place[][] hall;

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
        hall=new Place[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                Place place=new Place(i*80, j*80, PLACE_STATUS.STATUS_FREE);
                SeatView view=new SeatView(place);
                view.setLayoutX(i*80);
                view.setLayoutY(j*80);
                hallLayout.getChildren().add(view);
            }
        }
    }
}
