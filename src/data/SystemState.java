package data;

public class SystemState {

    private static User user=null;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SystemState.user = user;
    }
}
