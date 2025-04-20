package manageres;

import exceptions.CommandException;
import interfaces.UserManager;
import models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserManagerImpl implements UserManager {
    private final Map<String, User> users = new HashMap<>();
    private User currentUser = null;
    private final UserFileManager userFileManager;

    public UserManagerImpl() {
        this.userFileManager = new UserFileManager("users.txt");
        this.users.putAll(loadUsers());
    }

    private Map<String, User> loadUsers() {
        try {
            Map<String, User> loaded = userFileManager.loadUsers();
            if (loaded.isEmpty()) {
                loaded.put("admin", new User("admin", "i<2Java", true));
                userFileManager.saveUsers(loaded);
            }
            return loaded;
        } catch (IOException e) {
            System.out.println("Could not load users from file. Using default admin.");
            Map<String, User> fallback = new HashMap<>();
            fallback.put("admin", new User("admin", "admin123", true));
            return fallback;
        }
    }

    @Override
    public void login(String username, String password) throws CommandException {
        if (currentUser != null) {
            throw new CommandException("You are already logged in.");
        }
        User user = users.get(username.toLowerCase());
        if (user == null || !user.checkPassword(password)) {
            throw new CommandException("Invalid username or password.");
        }
        currentUser = user;

    }

    @Override
    public void logout() throws CommandException {
        if (currentUser == null) {
            throw new CommandException("You are not logged in.");
        }
        currentUser = null;
    }

    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    @Override
    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public boolean addUser(String username, String password) throws CommandException {
        String key = username.toLowerCase();
        if (!isAdmin()) {
            throw new CommandException("Only admin can add users.");
        }
        // username is unique
        if (users.containsKey(key)) {
            throw new CommandException("Username already exists.");
        }
        User newUser = new User(username, password, false);
        users.put(key, newUser);
        try {
            userFileManager.saveUsers(users);
        } catch (IOException e) {
            throw new CommandException("Could not save users to file.");
        }
        return true;
    }

    @Override
    public boolean removeUser(String username) throws CommandException {
        String key = username.toLowerCase();
        if (!isAdmin()) {
            throw new CommandException("Only admin can remove users.");
        }
        if (username.equals("admin")) {
            throw new CommandException("Cannot remove admin");
        }
        if (!users.containsKey(key)) {
            throw new CommandException("User does not exist.");
        }
        users.remove(key);
        try {
            userFileManager.saveUsers(users);
        } catch (IOException e) {
            throw new CommandException("Could not save users to file.");
        }
        return true;
    }
}
