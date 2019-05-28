package data;

/*
bd version
id - autoincrement integer
name - text
password - text
 */

import javafx.scene.image.Image;

import java.io.Serializable;

public class User implements IRandomShow, Serializable {
    private long id;
    private String name;
    private String password;
    private boolean isManager;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager(){
        return isManager;
    }

    public void setManager(boolean manager){
        isManager=manager;
    }

    @Override
    public Image getImage() {
        return null;
    }
}
