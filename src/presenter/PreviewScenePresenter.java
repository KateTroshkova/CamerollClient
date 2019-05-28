package presenter;

import data.Cinema;
import data.Movie;
import data.Session;
import data.User;
import model.connection.Connection;
import model.connection.RemoteRead;
import request.GetSessionRequest;

import java.util.ArrayList;
import java.util.HashSet;

public class PreviewScenePresenter extends BasePresenter<IMVPContract.IPreviewScene> implements RemoteRead.OnReaderListener {

    private Movie mData;
    private Cinema cData;
    private Session[] sessions;

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
        ArrayList<Session> appropriateSession=new ArrayList<>();
        for (Session session:sessions){
            if ((movie==null ||session.getMovie().getName().equals(movie)) &&
                    (cinema==null ||session.getCinema().getName().equals(cinema)) &&
                    (hall==null ||session.getHall().getName().equals(hall)) &&
                    (time==null ||session.getTime().equals(time)) &&
                    (date==null ||session.getDate().equals(date))){
                appropriateSession.add(session);
            }
        }
        Session[] res=new Session[appropriateSession.size()];
        for (int i=0; i<appropriateSession.size(); i++){
            res[i]=appropriateSession.get(i);
        }
        getView().onSessionDataReady(res);

        HashSet<String> uniqueMovie=new HashSet<>();
        HashSet<String> uniqueCinema=new HashSet<>();
        HashSet<String> uniqueDate=new HashSet<>();
        HashSet<String> uniqueTime=new HashSet<>();
        HashSet<String> uniqueHall=new HashSet<>();
        for(Session s:res){
            uniqueMovie.add(s.getMovie().getName());
            uniqueCinema.add(s.getCinema().getName());
            uniqueDate.add(s.getDate());
            uniqueTime.add(s.getTime());
            uniqueHall.add(s.getHall().getName());
        }
        getView().setMovies(uniqueMovie);
        getView().setCinemas(uniqueCinema);
        getView().setDates(uniqueDate);
        getView().setTimes(uniqueTime);
        getView().setHalls(uniqueHall);
        getView().update();
    }

    @Override
    public void onGetMovie(Movie[] data) {

    }

    @Override
    public void onGetCinema(Cinema[] data) {

    }

    @Override
    public void onGetSession(Session[] data) {
        this.sessions=data;
        HashSet<String> uniqueMovie=new HashSet<>();
        HashSet<String> uniqueCinema=new HashSet<>();
        HashSet<String> uniqueDate=new HashSet<>();
        HashSet<String> uniqueTime=new HashSet<>();
        HashSet<String> uniqueHall=new HashSet<>();
        for(Session s:data){
            uniqueMovie.add(s.getMovie().getName());
            uniqueCinema.add(s.getCinema().getName());
            uniqueDate.add(s.getDate());
            uniqueTime.add(s.getTime());
            uniqueHall.add(s.getHall().getName());
        }
        getView().onSessionDataReady(data);
        getView().setMovies(uniqueMovie);
        getView().setCinemas(uniqueCinema);
        getView().setDates(uniqueDate);
        getView().setTimes(uniqueTime);
        getView().setHalls(uniqueHall);
    }


    @Override
    public void onUser(User user) {

    }

    @Override
    public void onError(String error) {

    }
}
