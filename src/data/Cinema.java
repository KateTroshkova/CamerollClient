package data;

import javafx.scene.image.Image;
import model.RandomImageGenerator;

import java.io.Serializable;

/*
bd version
id - autoincrement integer
name - text
address - text
 */

/*
Связан с Сеансом
 */

public class Cinema implements Serializable, IRandomShow {

    private long id;
    private String name;
    private String address;

    public Cinema(){

    }

    public Cinema(String name, String address){
        this.name=name;
        this.address=address;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Image getImage() {
        return RandomImageGenerator.getInstance().getImage();
    }
}
