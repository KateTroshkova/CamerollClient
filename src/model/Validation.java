package model;

import data.SystemState;

public class Validation {

    public boolean inSystem(){
        return SystemState.getUser()!=null;
    }

    public boolean canBuy(){
        return SystemState.getUser().isManager();
    }
}
