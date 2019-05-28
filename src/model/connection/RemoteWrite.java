package model.connection;

import request.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RemoteWrite extends Thread {

    private ObjectOutputStream out;
    private boolean needToWrite=false;
    private ArrayList<Request> request;
    private boolean isRunning=true;

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

    public void stopWrite(){
        isRunning=false;
    }

    @Override
    public void run() {
        while (isRunning) {
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
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}