package request;

import data.Entrance;

public class SignInRequest implements Request {

    private Entrance entrance;

    public SignInRequest(Entrance entrance) {
        this.entrance = entrance;
    }

    public SignInRequest(){

    }

    public Entrance getEntrance() {
        return entrance;
    }

    public void setEntrance(Entrance entrance) {
        this.entrance = entrance;
    }
}
