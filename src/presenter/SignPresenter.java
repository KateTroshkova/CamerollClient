package presenter;

import data.Entrance;
import data.Registration;
import data.SystemState;
import data.User;

public class SignPresenter extends BasePresenter<IMVPContract.ISignScene> {
    @Override
    public void viewIsReady() {

    }

    public void register(Registration registration){
        User user=new User();
        user.setName(registration.getName());
        user.setPassword(registration.getPassword());
        user.setManager(registration.isManager());
        SystemState.setUser(user);
    }

    public void enter(Entrance entrance){
        User user=new User();
        user.setName(entrance.getName());
        user.setPassword(entrance.getPassword());
        SystemState.setUser(user);
    }
}