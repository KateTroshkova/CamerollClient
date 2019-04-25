package presenter;

import data.Cinema;
import data.Movie;

public class MainScenePresenter extends BasePresenter<IMVPContract.IMainScene> {
    @Override
    public void viewIsReady() {
        Movie[] mData=new Movie[8];
        mData[0]=new Movie("The Godfather", "", "", 1972, "");
        mData[1]=new Movie("The Shawshank Redemption", "", "", 1994, "");
        mData[2]=new Movie("Star Wars", "", "", 1977, "");
        mData[3]=new Movie("The Dark Knight", "", "", 2008, "");
        mData[4]=new Movie("The Matrix", "", "", 1999, "");
        mData[5]=new Movie("Fight Club", "", "", 1999, "");
        mData[6]=new Movie("Back to the Future", "", "", 1985, "");
        mData[7]=new Movie("The Silence of the Lambs", "", "", 1991, "");
        getView().onMovieDataReady(mData);
        Cinema[] cData=new Cinema[4];
        cData[0]=new Cinema("Аврора", "");
        cData[1]=new Cinema("Синема парк", "");
        cData[2]=new Cinema("Победа", "");
        cData[3]=new Cinema("Киносити", "");
        getView().onCinemaDataReady(cData);
    }
}
