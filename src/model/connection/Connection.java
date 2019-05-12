package model.connection;

import javafx.application.Platform;
import presenter.IMVPContract;
import request.GetMoviesRequest;
import request.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private static Connection ourInstance = new Connection();
    private Socket socket;
    private RemoteRead in;
    private RemoteWrite out;

    public static Connection getInstance() {
        return ourInstance;
    }

    private Connection() {
        try {
            socket=new Socket("localhost", 8331);
            out = new RemoteWrite(socket);
            out.start();
            in = new RemoteRead(socket);
            new Thread(in).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainController(IMVPContract.IMainScene controller){
        in.setMainController(controller);
    }

    public void setPreviewController(IMVPContract.IPreviewScene controller){
        in.setPreviewController(controller);
    }

    public void send(Request request){
        out.sendRequest(request);
    }

    public void addReadListener(RemoteRead.OnReaderListener listener){
        in.addListener(listener);
    }
}
