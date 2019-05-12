package view;

import data.Cinema;
import data.Entrance;
import data.Movie;
import data.Registration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import presenter.IMVPContract;
import presenter.IMoveListener;
import presenter.MainScenePresenter;
import view.customView.CinemaView;
import view.customView.MovieView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/*
Общение с gui происходит через контроллер.
Поля должны быть помечены FXML, чтобы связаться с разметкой.
Поля должны иметь то же название, что и id разметки.
Обновление экрана происходит только через initialize().
Его можно вызывать из других методов.
 */

public class MainScene extends SignableScene implements IMVPContract.IMainScene{

    @FXML
    private FlowPane movies;
    @FXML
    private FlowPane cinemas;
    private Movie[] mData;
    private Cinema[] cData;
    private MainScenePresenter presenter;
    private ArrayList<IMoveListener> listeners;

    public MainScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_screen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        listeners=new ArrayList<>();
        registerMenuAction();
    }

    @Override
    public void onMovieDataReady(Movie[] data) {
        this.mData=data;
        //initialize();
    }

    @Override
    public void onCinemaDataReady(Cinema[] data) {
        this.cData=data;
        //initialize();
    }

    @Override
    public void update() {
        initialize();
    }

    @Override
    public void openSignInDialog() {

    }

    @Override
    public void openSignUpDialog() {

    }

    public void addMoveListener(IMoveListener listener){
        listeners.add(listener);
    }

    @FXML
    public void initialize(){
        if (mData==null && cData==null) {
            presenter = new MainScenePresenter();
            presenter.attachView(this);
            presenter.viewIsReady();
        }
        else {
            movies.getChildren().clear();
            if (mData!=null) {
                for (int i = 0; i < mData.length; i++) {
                    MovieView view=new MovieView(mData[i]);
                    view.setOnMouseClicked(event -> {
                        for(IMoveListener listener:listeners){
                            listener.mainToPreview(view.getData());
                        }
                    });
                    movies.getChildren().add(view);
                }
            }
            cinemas.getChildren().clear();
            if (cData!=null) {
                for (int i = 0; i < cData.length; i++) {
                    CinemaView view=new CinemaView(cData[i]);
                    view.setOnMouseClicked(event -> {
                        for(IMoveListener listener:listeners){
                            listener.mainToPreview(view.getData());
                        }
                    });
                    cinemas.getChildren().add(view);
                }
            }
        }
    }
}
