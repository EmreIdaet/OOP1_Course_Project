package manageres;

import exceptions.CommandException;
import interfaces.UserManager;
import models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserManagerImpl implements UserManager {
    /**
     * This class manages user accounts in the library system.
     * It provides methods for user login, logout, and account management.
     */
    private final Map<String, User> users = new HashMap<>();
    private User currentUser = null;
    private final UserFileManager userFileManager;

    /**
     * Constructor to initialize the UserManagerImpl with a specific filename.
     * It loads users from the file and initializes the user map.
     */
    public UserManagerImpl() {
        this.userFileManager = new UserFileManager("users.txt");
        this.users.putAll(loadUsers());
    }

    /**
     * Loads users from the specified file.
     * If the file is empty, it creates a default admin user.
     *
     * @return A map of usernames to User objects.
     */
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

    /**
     * Logs in a user with the given username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @throws CommandException If there is an error during command execution.
     */
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

    /**
     * Logout the current user.
     *
     * @throws CommandException If there is an error during command execution.
     */
    @Override
    public void logout() throws CommandException {
        if (currentUser == null) {
            throw new CommandException("You are not logged in.");
        }
        currentUser = null;
    }

    /**
     * Checks if the user is logged in.
     *
     * @return true if the user is logged in, false otherwise.
     */
    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Checks if the current user is an admin.
     *
     * @return true if the user is an admin, false otherwise.
     */
    @Override
    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }

    /**
     * Gets the current user.
     *
     * @return The current user.
     */
    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Adds a new user to the library system.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @throws CommandException If there is an error during command execution.
     */
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

    /**
     * Removes a user from the library system.
     *
     * @param username The username of the user to be removed.
     * @throws CommandException If there is an error during command execution.
     */
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
