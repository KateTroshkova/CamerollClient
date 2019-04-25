package view;

import data.Cinema;
import data.Movie;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import presenter.IMVPContract;
import presenter.MainScenePresenter;
import view.customView.CinemaView;
import view.customView.MovieView;

/*
Общение с gui происходит через контроллер.
Поля должны быть помечены FXML, чтобы связаться с разметкой.
Поля должны иметь то же название, что и id разметки.
Обновление экрана происходит только через initialize().
Его можно вызывать из других методов.
 */

public class MainScene implements IMVPContract.IMainScene{

    @FXML
    private FlowPane movies;
    @FXML
    private FlowPane cinemas;
    private Movie[] mData;
    private Cinema[] cData;
    private MainScenePresenter presenter;

    @Override
    public void onMovieDataReady(Movie[] data) {
        this.mData=data;
        initialize();
    }

    @Override
    public void onCinemaDataReady(Cinema[] data) {
        this.cData=data;
        initialize();
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
                    movies.getChildren().add(new MovieView(mData[i]));
                }
            }
            cinemas.getChildren().clear();
            if (cData!=null) {
                for (int i = 0; i < cData.length; i++) {
                    cinemas.getChildren().add(new CinemaView(cData[i]));
                }
            }
        }
    }
}
