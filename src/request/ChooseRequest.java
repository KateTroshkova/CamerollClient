package request;

import data.Session;
import data.User;

public abstract class ChooseRequest implements Request {
    protected User user;
    protected Session session;
    protected int x;
    protected int y;

    public ChooseRequest(User user, Session session, int x, int y) {
        this.user = user;
        this.session = session;
        this.x = x;
        this.y = y;
    }

    public ChooseRequest(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void updatePattern();
}
