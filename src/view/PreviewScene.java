package view;

import data.Cinema;
import data.Movie;
import data.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import model.connection.Connection;
import presenter.IMVPContract;
import presenter.IMoveListener;
import presenter.PreviewScenePresenter;
import view.customView.SessionView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PreviewScene extends SignableScene implements IMVPContract.IPreviewScene{

    private PreviewScenePresenter presenter;
    @FXML
    private FlowPane sessions;

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

    @FXML
    private MenuItem backButton;

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
        listeners=new ArrayList<>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("preview_screen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        registerMenuAction();
    }

    @Override
    public void onSessionDataReady(Session[] data) {
        this.sData=data;
        //initialize();
    }

    @Override
    public void setMovies(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        movieSort.getItems().clear();
        movieSort.getItems().addAll(sortedData);
    }

    @Override
    public void setCinemas(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        cinemaSort.getItems().clear();
        cinemaSort.getItems().addAll(sortedData);
    }

    @Override
    public void setHalls(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        hallSort.getItems().clear();
        hallSort.getItems().addAll(sortedData);
    }

    @Override
    public void setDates(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        dateSort.getItems().clear();
        dateSort.getItems().addAll(sortedData);
    }

    @Override
    public void setTimes(HashSet<String> data) {
        Object[] sortedData=data.toArray();
        Arrays.sort(sortedData);
        timeSort.getItems().clear();
        timeSort.getItems().addAll(sortedData);
    }

    @Override
    public void update() {
        initialize();
    }

    @FXML
    private void initialize(){
        if (sData==null){
            presenter=new PreviewScenePresenter();
            if (mData!=null){
                presenter.alertMovie(mData);
            }
            else{
                presenter.alertCinema(cData);
            }
            presenter.attachView(this);
            presenter.viewIsReady();
        }
        else {
            sessions.getChildren().clear();
            if (sData != null) {
                for (Session session : sData) {
                    SessionView view = new SessionView(session);
                    sessions.getChildren().add(view);
                }
                for(int i=0; i<sessions.getChildren().size(); i++){
                    sessions.getChildren().get(i).setOnMouseClicked(event -> {
                            SessionView view= (SessionView) event.getSource();
                        for (IMoveListener listener : listeners) {
                            listener.previewToChoose(view.getData());
                        }
                    });
                }
            }
        }
        backButton.setOnAction(event -> {
         for(IMoveListener listener:listeners){
            listener.previewToMain();
         }
         Connection.getInstance().removeListener(presenter);
         Connection.getInstance().removePreviewController();
         });
            findButton.setOnMouseClicked(event -> {
                SingleSelectionModel mSort=movieSort.getSelectionModel();
                SingleSelectionModel cSort=cinemaSort.getSelectionModel();
                SingleSelectionModel hSort=hallSort.getSelectionModel();
                SingleSelectionModel tSort=timeSort.getSelectionModel();
                SingleSelectionModel dSort=dateSort.getSelectionModel();
                    presenter.onFilterClick(mSort.isEmpty()?null:mSort.getSelectedItem().toString(),
                            cSort.isEmpty()?null:cSort.getSelectedItem().toString(),
                            hSort.isEmpty()?null:hSort.getSelectedItem().toString(),
                            tSort.isEmpty()?null:tSort.getSelectedItem().toString(),
                            dSort.isEmpty()?null:dSort.getSelectedItem().toString());
            });
            if (isMovie()) {
                initMovieInfo();
            } else {
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
