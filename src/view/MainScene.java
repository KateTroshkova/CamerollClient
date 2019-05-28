package view;

import data.Cinema;
import data.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import presenter.IMVPContract;
import presenter.IMoveListener;
import presenter.MainScenePresenter;
import view.customView.CinemaView;
import view.customView.MovieView;

import java.io.IOException;
import java.util.ArrayList;

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

    public void addMoveListener(IMoveListener listener){
        listeners.add(listener);
    }

    @FXML
    private void initialize(){
        if (mData==null && cData==null) {
            presenter = new MainScenePresenter();
            presenter.attachView(this);
            presenter.viewIsReady();
        }
        else {
            movies.getChildren().clear();
            if (mData!=null) {
                for (Movie aMData : mData) {
                    MovieView view = new MovieView(aMData);
                    view.setOnMouseClicked(event -> {
                        for (IMoveListener listener : listeners) {
                            listener.mainToPreview(view.getData());
                        }
                    });
                    movies.getChildren().add(view);
                }
            }
            cinemas.getChildren().clear();
            if (cData!=null) {
                for (Cinema aCData : cData) {
                    CinemaView view = new CinemaView(aCData);
                    view.setOnMouseClicked(event -> {
                        for (IMoveListener listener : listeners) {
                            listener.mainToPreview(view.getData());
                        }
                    });
                    cinemas.getChildren().add(view);
                }
            }
        }
    }
}
