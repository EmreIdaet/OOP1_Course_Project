package interfaces;

import exceptions.CommandException;
import models.User;

public interface UserManager {
    /**
     * This interface is used to manage user accounts in the library system.
     * It provides methods for user login, logout, and account management.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @throws CommandException If there is an error during command execution.
     */
    void login(String username, String password) throws CommandException;
    /**
     * Logs out the current user from the library system.
     *
     * @throws CommandException If there is an error during command execution.
     */
    void logout() throws CommandException;
    /**
     * Checks if the user is logged in.
     *
     * @return true if the user is logged in, false otherwise.
     */
    boolean isLoggedIn();
    /**
     * Checks if the current user is an admin.
     *
     * @return true if the user is an admin, false otherwise.
     */
    boolean isAdmin();
    /**
     * Gets the current user.
     *
     * @return The current user.
     */
    User getCurrentUser();
    /**
     * Adds a new user to the library system.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @throws CommandException If there is an error during command execution.
     */
    boolean addUser(String username, String password) throws CommandException;
    /**
     * Removes a user from the library system.
     *
     * @param username The username of the user to be removed.
     * @throws CommandException If there is an error during command execution.
     */
    boolean removeUser(String username) throws CommandException;
}
