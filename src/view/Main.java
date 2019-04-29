package view;

import data.Cinema;
import data.Movie;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presenter.IMoveListener;

public class Main extends Application implements IMoveListener {

    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("cameroll");
        MainScene mainScene=new MainScene();
        mainScene.addMoveListener(this);
        scene=new Scene(mainScene, 1600, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mainToPreview(Movie movie) {
        scene.setRoot(new PreviewScene(movie));
    }

    @Override
    public void mainToPreview(Cinema cinema) {
        scene.setRoot(new PreviewScene(cinema));
    }
}
