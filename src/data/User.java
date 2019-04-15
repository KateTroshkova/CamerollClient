package data;

/*
bd version
id - autoincrement integer
name - text
password - text
 */

import javafx.scene.image.Image;

public class User implements IRandomShow {
    private long id;
    private String name;
    private String password;

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
        return false;
    }

    @Override
    public Image getImage() {
        return null;
    }
}
