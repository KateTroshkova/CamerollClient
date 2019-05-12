package request;

import data.Session;
import data.User;

public class BuyRequest implements Request {

    private User user;
    private Session session;
    private int x;
    private int y;

    public BuyRequest(User user, Session session, int x, int y) {
        this.user = user;
        this.session = session;
        this.x = x;
        this.y = y;
    }

    public BuyRequest(){

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
}
