package data;

import java.io.Serializable;

public class Registration implements Serializable{

    private String name;
    private String password;
    private String confirm;
    private String additionalPassword;
    private boolean isManager;

    public Registration(String name, String password, String confirm, String additionalPassword, boolean isManager) {
        this.name = name;
        this.password = password;
        this.confirm = confirm;
        this.additionalPassword = additionalPassword;
        this.isManager = isManager;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm() {
        return confirm;
    }

    public String getAdditionalPassword() {
        return additionalPassword;
    }

    public boolean isManager() {
        return isManager;
    }
}
