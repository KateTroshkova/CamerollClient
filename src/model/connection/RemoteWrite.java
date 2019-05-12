package model.connection;

import request.GetMoviesRequest;
import request.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RemoteWrite extends Thread {

    private ObjectOutputStream out;
    private int count=0;
    private boolean needToWrite=false;
    private ArrayList<Request> request;

    public RemoteWrite(Socket client){
        request=new ArrayList<>();
        try {
            out=new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(Request request){
        this.request.add(request);
        needToWrite=true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (needToWrite) {
                    for(Request request:this.request) {
                        out.writeObject(request); // отправляем на сервер
                        out.flush();
                    }
                    request.clear();
                    needToWrite=false;
                }
                else{
                    sleep(100);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}