package view.customView;

import data.Cinema;
import data.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import res.R;

import java.io.IOException;

public class CinemaView extends FlowPane {

    @FXML
    private ImageView image;
    @FXML
    private Label text;

    private Cinema data;

    public CinemaView(Cinema cinema) {
        super();
        this.data=cinema;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view_cinema.fxml"));
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
        text.setText(data.getName());
        Image res = new Image(R.image.ic_cinema);
        image.setImage(res);
        image.setCache(true);
    }
}