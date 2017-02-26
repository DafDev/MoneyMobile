package beans;

import java.util.HashMap;
import java.util.List;

public class UserMap {
    private static UserMap userMap = new UserMap();

    public static UserMap getInstance() {
        return userMap;
    }

    private UserMap() {
        users = new HashMap<String, String>();
    }

    private volatile static HashMap<String, String> users;
    public static HashMap<String, String> getUsers() {
        return users;
    }
}
