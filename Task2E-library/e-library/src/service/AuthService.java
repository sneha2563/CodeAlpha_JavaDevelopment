package service;

import model.User;

import java.util.HashMap;

public class AuthService {
    private HashMap<String, User> users = new HashMap<>();

    public AuthService() {
        users.put("admin", new User("admin", "1234"));
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        return user != null && user.checkPassword(password);
    }

    public void register(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }
}
