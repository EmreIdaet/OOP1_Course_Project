package models;

public class User {
    /**
     * This class represents a user in the library system. It contains the user's username, password,
     * and a flag indicating whether the user is an admin.
     */
    private String username;
    private String password;
    private boolean isAdmin;

    /**
     * Constructor to create a User object with the specified username, password, and admin status.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param isAdmin  Indicates whether the user is an admin.
     */
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Checks if the provided password matches the user's password.
     *
     * @param password The password to check.
     * @return true if the passwords match, false otherwise.
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
