package model.connection;

import data.Cinema;
import data.Movie;
import data.Session;
import data.User;
import javafx.application.Platform;
import presenter.IMVPContract;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RemoteRead implements Runnable {

    private ObjectInputStream in;
    private IMVPContract.IMainScene mainController;
    private IMVPContract.IPreviewScene previewController;
    private IMVPContract.IChooseScene chooseController;
    private IMVPContract.ISignScene signController;
    private boolean isRunning=true;
    private ArrayList<OnReaderListener> listeners;

    public interface OnReaderListener{
        void onGetMovie(Movie[] data);
        void onGetCinema(Cinema[] data);
        void onGetSession(Session[] data);
        void onUser(User user);
        void onError(String error);
    }


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

    public void setChooseController(IMVPContract.IChooseScene controller){
        this.chooseController=controller;
    }

    public void setSignController(IMVPContract.ISignScene controller){
        this.signController=controller;
    }

    public void addListener(OnReaderListener listener){
        this.listeners.add(listener);
    }

    public void stopRead(){
        isRunning=false;
    }

    public void removePreviewController(){
        previewController=null;
    }

    public void removeListener(OnReaderListener listener){
        this.listeners.remove(listener);
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                int code=in.readInt();
                switch (code){
                    case 0:{
                        Movie[] movies= (Movie[]) in.readObject();
                        for(OnReaderListener listener:listeners){
                            listener.onGetMovie(movies);
                        }
                        Platform.runLater(() -> mainController.update());
                        break;
                    }
                    case 1:{
                        Cinema[] cinemas= (Cinema[]) in.readObject();
                        for(OnReaderListener listener:listeners){
                            listener.onGetCinema(cinemas);
                        }
                        Platform.runLater(() -> mainController.update());
                        break;
                    }
                    case 2:{
                        Session[] sessions= (Session[]) in.readObject();
                        for(OnReaderListener listener:listeners){
                            listener.onGetSession(sessions);
                        }
                        Platform.runLater(() -> {
                            if (previewController!=null) {
                                previewController.update();
                            }
                        });
                        break;
                    }
                    case 3:{
                        User user= (User) in.readObject();
                        for(OnReaderListener listener:listeners){
                            listener.onUser(user);
                        }
                        break;
                    }
                    case 4:{
                        int e = in.readInt();
                        String error="Неизвестная ошибка";
                        for(OnReaderListener listener:listeners){
                            switch (e){
                                case 1000:{
                                    error="Возникли проблемы при доступе в систему. Возможно, вы ввели неверные данные";
                                    break;
                                }
                                case 2000:{
                                    error="Невозможная операция. Место уже занято.";
                                    break;
                                }
                            }
                            listener.onError(error);
                        }
                        String finalError = error;
                        Platform.runLater(() -> signController.showError(finalError));
                        break;
                    }
                    case 5:{
                        Session session= (Session) in.readObject();
                        Platform.runLater(() -> chooseController.update(session));
                        break;
                    }
                }
            }
        } catch (IOException ignored) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}