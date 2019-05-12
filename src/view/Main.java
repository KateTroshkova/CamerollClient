package view;

import data.Cinema;
import data.Movie;
import data.Session;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presenter.IMoveListener;

public class Main extends Application implements IMoveListener {

    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("cameroll");
        MainScene mainScene=new MainScene();
        mainScene.addMoveListener(this);
        scene=new Scene(mainScene, 1600, 800);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMinWidth(scene.getWidth());
        primaryStage.setMinHeight(scene.getHeight());
        this.stage=primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mainToPreview(Movie movie) {
        PreviewScene previewScene=new PreviewScene(movie);
        previewScene.addMoveListener(this);
        scene.setRoot(previewScene);
    }

    @Override
    public void mainToPreview(Cinema cinema) {
        PreviewScene previewScene=new PreviewScene(cinema);
        previewScene.addMoveListener(this);
        scene.setRoot(previewScene);
    }

    @Override
    public void previewToChoose(Session session) {
        ChooseScene chooseScene=new ChooseScene(session);
        scene.setRoot(chooseScene);
        stage.sizeToScene();
        stage.setMinWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());
    }
}
