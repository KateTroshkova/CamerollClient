package view;

import data.Cinema;
import data.Movie;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import presenter.IMVPContract;
import presenter.MainScenePresenter;

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
    private Movie[] mData;
    private MainScenePresenter presenter;

    @Override
    public void onMovieDataReady(Movie[] data) {
        this.mData=data;
        initialize();
    }

    @Override
    public void onCinemaDataReady(Cinema[] data) {
    }

    @FXML
    public void initialize(){
        if (mData==null) {
            presenter = new MainScenePresenter();
            presenter.attachView(this);
            presenter.viewIsReady();
        }
        else {
            for (int i = 0; i < mData.length; i++) {
                movies.getChildren().add(new MovieView(mData[i]));
            }
        }
    }
}
