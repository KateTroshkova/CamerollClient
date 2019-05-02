package view;

import data.Cinema;
import data.Movie;
import data.Session;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import presenter.IMVPContract;
import presenter.IMoveListener;
import presenter.PreviewScenePresenter;
import view.customView.SessionView;

import java.io.IOException;
import java.util.*;

public class PreviewScene extends BorderPane implements IMVPContract.IPreviewScene{

    private PreviewScenePresenter presenter;
    @FXML
    private VBox sessions;

    @FXML
    private ImageView image;

    @FXML
    private Label description;

    @FXML
    private ComboBox movieSort;
    @FXML
    private ComboBox cinemaSort;
    @FXML
    private ComboBox hallSort;
    @FXML
    private ComboBox dateSort;
    @FXML
    private ComboBox timeSort;
    @FXML
    private Button findButton;

    private Session[] sData;
    private ArrayList<IMoveListener> listeners;

    private Movie mData;
    private Cinema cData;

    public PreviewScene(Movie movie){
        this.mData=movie;
        loadFXML();
    }

    public PreviewScene(Cinema cinema){
        this.cData=cinema;
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
    public void setMovies(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        movieSort.getItems().addAll(sortedData);
    }

    @Override
    public void setCinemas(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        cinemaSort.getItems().addAll(sortedData);
    }

    @Override
    public void setHalls(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        hallSort.getItems().addAll(sortedData);
    }

    @Override
    public void setDates(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        dateSort.getItems().addAll(sortedData);
    }

    @Override
    public void setTimes(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        timeSort.getItems().addAll(sortedData);
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
        if (isMovie()){
            initMovieInfo();
        }
        else{
            initCinemaInfo();
        }
    }

    private void initMovieInfo(){
        image.setImage(mData.getImage());
        description.setText(mData.getName().toUpperCase()+"\n"+
                mData.getDescription()+"\n"+
                "В ролях: "+mData.getActors()+"\n"+
                "Жанр: "+mData.getGenre()+"\n"+
                "Страна: "+mData.getCountry());
    }

    private void initCinemaInfo(){
        image.setImage(cData.getImage());
        description.setText(cData.getName().toUpperCase()+"\n"+cData.getAddress());
    }

    private boolean isMovie(){
        return mData!=null;
    }

}
