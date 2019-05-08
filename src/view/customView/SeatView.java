package view.customView;

import data.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import res.R;

import java.awt.*;
import java.io.IOException;

public class SeatView extends BorderPane{

    @FXML
    private Circle seatShape;

    private Place data;

    public SeatView(Place place) {
        super();
        this.data=place;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view_seat.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize();
    }

    public Place getData(){
        return data;
    }

    public void mark(){
        seatShape.setFill(Color.BLACK);
    }

    public void updateState(PLACE_STATUS state){
        switch(state){
            case STATUS_FREE:{
                data.setFree();
                break;
            }
            case STATUS_BOOKED:{
                data.setBooked();
                break;
            }
            case STATUS_TAKEN:{
                data.setTaken();
                break;
            }
            default:{
                break;
            }
        }
        initialize();
    }

    @FXML
    private void initialize(){
        seatShape.setStrokeWidth(0);
        if (data.isFree()){
            seatShape.setFill(javafx.scene.paint.Color.BLUE);
        }
        if (data.isBooked()){
            seatShape.setFill(Color.ORANGE);
        }
        if (data.isTaken()){
            seatShape.setFill(javafx.scene.paint.Color.RED);
        }
        if (!data.isExist()){
            seatShape.setFill(javafx.scene.paint.Color.WHITE);
        }
    }
}