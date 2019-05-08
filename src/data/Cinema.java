package data;

import javafx.scene.image.Image;
import model.RandomImageGenerator;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return id == cinema.id &&
                Objects.equals(name, cinema.name) &&
                Objects.equals(address, cinema.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address);
    }
}
