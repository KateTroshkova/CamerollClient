package presenter;

import data.Cinema;
import data.Hall;
import data.Movie;
import data.Session;
import model.connection.Connection;
import model.connection.RemoteRead;
import org.w3c.dom.CDATASection;
import request.GetMoviesRequest;
import request.GetSessionRequest;

import java.util.ArrayList;
import java.util.HashSet;

public class PreviewScenePresenter extends BasePresenter<IMVPContract.IPreviewScene> implements RemoteRead.OnReaderListener {

    private Movie mData;
    private Cinema cData;

    @Override
    public void viewIsReady() {
        Connection.getInstance().addReadListener(this);
        Connection.getInstance().setPreviewController(getView());
        if (mData!=null) {
            Connection.getInstance().send(new GetSessionRequest(mData));
        }
        if (cData!=null){
            Connection.getInstance().send(new GetSessionRequest(cData));
        }
    }

    public void alertMovie(Movie movie){
        mData=movie;
    }

    public void alertCinema(Cinema cinema){
        cData=cinema;
    }

    public void onFilterClick(String movie, String cinema, String hall, String time, String date){
       /** ArrayList<Session> data=new ArrayList<>();
        for (Session session:sessions){
            if ((movie.isEmpty() ||session.getMovie().getName().equals(movie)) &&
                    (cinema.isEmpty() ||session.getCinema().getName().equals(cinema)) &&
                    (hall.isEmpty() ||session.getHall().getName().equals(hall)) &&
                    (time.isEmpty() ||session.getTime().equals(time)) &&
                    (date.isEmpty() ||session.getDate().equals(date))){
                data.add(session);
            }
        }
        Session[] res=new Session[data.size()];
        for (int i=0; i<data.size(); i++){
            res[i]=data.get(i);
        }
        HashSet<String> uniqueMovie=new HashSet<>();
        HashSet<String> uniqueCinema=new HashSet<>();
        HashSet<String> uniqueDate=new HashSet<>();
        HashSet<String> uniqueTime=new HashSet<>();
        for(Session s:res){
            uniqueMovie.add(s.getMovie().getName());
            uniqueCinema.add(s.getCinema().getName());
            uniqueDate.add(s.getDate());
            uniqueTime.add(s.getTime());
        }
        getView().onSessionDataReady(res);
        getView().setMovies(uniqueMovie);
        getView().setCinemas(uniqueCinema);
        getView().setDates(uniqueDate);
        getView().setTimes(uniqueTime);*/
    }

    @Override
    public void onGetMovie(Movie[] data) {

    }

    @Override
    public void onGetCinema(Cinema[] data) {

    }

    @Override
    public void onGetSession(Session[] data) {
        HashSet<String> uniqueMovie=new HashSet<>();
        HashSet<String> uniqueCinema=new HashSet<>();
        HashSet<String> uniqueDate=new HashSet<>();
        HashSet<String> uniqueTime=new HashSet<>();
        for(Session s:data){
            uniqueMovie.add(s.getMovie().getName());
            uniqueCinema.add(s.getCinema().getName());
            uniqueDate.add(s.getDate());
            uniqueTime.add(s.getTime());
        }
        getView().onSessionDataReady(data);
        getView().setMovies(uniqueMovie);
        getView().setCinemas(uniqueCinema);
        getView().setDates(uniqueDate);
        getView().setTimes(uniqueTime);
    }
}
