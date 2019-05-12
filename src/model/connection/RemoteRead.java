package model.connection;

import data.Cinema;
import data.Movie;
import data.Session;
import javafx.application.Platform;
import presenter.IMVPContract;
import view.MainScene;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RemoteRead implements Runnable {

    private ObjectInputStream in;
    private IMVPContract.IMainScene mainController;
    private IMVPContract.IPreviewScene previewController;

    public interface OnReaderListener{
        void onGetMovie(Movie[] data);
        void onGetCinema(Cinema[] data);
        void onGetSession(Session[] data);
    }

    private ArrayList<OnReaderListener> listeners;

    public RemoteRead(Socket client){
        try {
            in=new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listeners=new ArrayList<>();
    }

    public void setMainController(IMVPContract.IMainScene controller){
        this.mainController=controller;
    }

    public void setPreviewController(IMVPContract.IPreviewScene controller){
        this.previewController=controller;
    }

    public void addListener(OnReaderListener listener){
        this.listeners.add(listener);
    }

    @Override
    public void run() {
        try {
            while (true) {
                int code=in.readInt();
                switch (code){
                    case 0:{
                        Movie[] movies= (Movie[]) in.readObject();
                        System.out.println(movies);
                        for(OnReaderListener listener:listeners){
                            listener.onGetMovie(movies);
                        }
                        Platform.runLater(() -> {
                            mainController.update();
                        });
                        break;
                    }
                    case 1:{
                        Cinema[] cinemas= (Cinema[]) in.readObject();
                        for(OnReaderListener listener:listeners){
                            listener.onGetCinema(cinemas);
                        }
                        Platform.runLater(() -> {
                            mainController.update();
                        });
                        break;
                    }
                    case 2:{
                        Session[] sessions= (Session[]) in.readObject();
                        System.out.println(sessions);
                        for(OnReaderListener listener:listeners){
                            listener.onGetSession(sessions);
                        }
                        Platform.runLater(() -> {
                            previewController.update();
                        });
                        break;
                    }
                }
                //Movie movie = (Movie) in.readObject();
                //System.out.println(movie);
            }
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}