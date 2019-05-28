package view;

import data.Cinema;
import data.Movie;
import data.Session;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.connection.Connection;
import presenter.Cache;
import presenter.IMoveListener;

public class Main extends Application implements IMoveListener {

    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("cameroll");
        MainScene mainScene=new MainScene();
        mainScene.addMoveListener(this);
        scene=new Scene(mainScene, 1600, 800);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMinWidth(scene.getWidth());
        primaryStage.setMinHeight(scene.getHeight());
    }

    @Override
    public void stop(){
        Connection.getInstance().stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mainToPreview(Movie movie) {
        PreviewScene previewScene=new PreviewScene(movie);
        previewScene.addMoveListener(this);
        Cache.getInstance().setMovie(movie);
        scene.setRoot(previewScene);
    }

    @Override
    public void mainToPreview(Cinema cinema) {
        PreviewScene previewScene=new PreviewScene(cinema);
        previewScene.addMoveListener(this);
        Cache.getInstance().setCinema(cinema);
        scene.setRoot(previewScene);
    }

    @Override
    public void previewToChoose(Session session) {
        ChooseScene chooseScene=new ChooseScene(session);
        chooseScene.addMoveListener(this);
        scene.setRoot(chooseScene);
    }

    @Override
    public void chooseToPreview() {
        if (Cache.getInstance().getMovie()!=null){
            PreviewScene previewScene=new PreviewScene(Cache.getInstance().getMovie());
            previewScene.addMoveListener(this);
            scene.setRoot(previewScene);
        }
        else{
            PreviewScene previewScene=new PreviewScene(Cache.getInstance().getCinema());
            previewScene.addMoveListener(this);
            scene.setRoot(previewScene);
        }
    }

    @Override
    public void previewToMain() {
        MainScene mainScene=new MainScene();
        mainScene.addMoveListener(this);
        scene.setRoot(mainScene);
        Cache.getInstance().setCinema(null);
        Cache.getInstance().setMovie(null);
    }
}
