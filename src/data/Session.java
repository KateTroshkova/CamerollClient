package data;

import java.io.Serializable;

public class Session implements Serializable {

    private long id;
    private Movie movie;
    private Cinema cinema;
    private Hall hall;
    private String time;
    private String date;
    private int price;
}
