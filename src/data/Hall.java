package data;

import java.io.Serializable;

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
}
