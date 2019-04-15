package data;

import java.io.Serializable;

/*
bd version
id - autoincrement integer
name - text
placePattern - text
 */

/*
Связан с Сеансом
 */

public class Hall implements Serializable {

    private long id;
    private String name;
    private String placePattern;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlacePattern() {
        return placePattern;
    }

    public void setPlacePattern(String placePattern) {
        this.placePattern = placePattern;
    }

    public Place getPlace(int x, int y){
        return null;
    }

    public int getWidth(){
        return 0;
    }

    public int getHeight(){
        return 0;
    }
}
