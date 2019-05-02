package view.customView;

import data.Cinema;
import data.Movie;
import data.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import res.R;

import java.io.IOException;

public class SessionView extends BorderPane{

    @FXML
    private ImageView image;
    @FXML
    private Label movieInfo;
    @FXML
    private Label cinemaInfo;
    @FXML
    private Label timeInfo;
    @FXML
    private Label price;

    private Session data;

    public SessionView(Session session) {
        super();
        this.data=session;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view_session.fxml"));
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
        image.setImage(data.getCinema().getImage());
        movieInfo.setText(data.getMovie().getName());
        cinemaInfo.setText(data.getCinema().getName()+"\n"+data.getHall().getName());
        timeInfo.setText(data.getDate()+"\n"+data.getTime());
        price.setText(data.getPrice()+"руб.");
    }
}