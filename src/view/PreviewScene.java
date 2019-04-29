package view;

import data.Cinema;
import data.Movie;
import data.Session;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import presenter.IMVPContract;
import presenter.IMoveListener;
import presenter.PreviewScenePresenter;
import view.customView.SessionView;

import java.io.IOException;
import java.util.ArrayList;

public class PreviewScene extends BorderPane implements IMVPContract.IPreviewScene{

    private PreviewScenePresenter presenter;
    @FXML
    private VBox sessions;

    private Session[] sData;
    private ArrayList<IMoveListener> listeners;

    public PreviewScene(Movie movie){
       loadFXML();
    }

    public PreviewScene(Cinema cinema){
        loadFXML();
    }

    public void addMoveListener(IMoveListener listener){
        listeners.add(listener);
    }

    private void loadFXML(){
        presenter=new PreviewScenePresenter();
        listeners=new ArrayList<>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("preview_screen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    public void onSessionDataReady(Session[] data) {
        this.sData=data;
        initialize();
    }

    @Override
    public void setMovies(ArrayList<String> data) {

    }

    @Override
    public void setCinemas(ArrayList<String> data) {

    }

    @Override
    public void setHalls(ArrayList<String> data) {

    }

    @Override
    public void setDates(ArrayList<String> data) {

    }

    @Override
    public void setTimes(ArrayList<String> data) {

    }

    @FXML
    private void initialize(){
        if (sData!=null) {
            for (Session session : sData) {
                SessionView view = new SessionView(session);
                view.setOnMouseClicked(event -> {
                    for(IMoveListener listener:listeners){
                        listener.previewToChoose(session);
                    }
                });
                sessions.getChildren().add(view);
            }
        }
    }

}
