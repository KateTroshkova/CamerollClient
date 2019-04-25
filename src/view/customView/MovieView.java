package view.customView;

import data.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class MovieView extends FlowPane{

    @FXML
    private ImageView image;
    @FXML
    private Label text;

    private Movie data;

    public MovieView(Movie movie) {
        super();
        this.data=movie;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view_movie.fxml"));
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
        Image res = new Image("res/drawable/back.jpg");
        image.setImage(res);
        image.setCache(true);
    }
}
