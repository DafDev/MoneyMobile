package beans;


public class ActiveUser {

    private static ActiveUser activeUser = new ActiveUser();

    public static ActiveUser getInstance() { return activeUser; }

    private ActiveUser() {}

    private static User user = null;
    public static void setUser(User user1) { user = user1; }
    public static User getUser() { return user; }
}
