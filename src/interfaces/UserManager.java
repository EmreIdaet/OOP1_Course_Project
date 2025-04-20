package interfaces;

import exceptions.CommandException;
import models.User;

public interface UserManager {
    void login(String username, String password) throws CommandException;
    void logout() throws CommandException;
    boolean isLoggedIn();
    boolean isAdmin();
    User getCurrentUser();
    boolean addUser(String username, String password) throws CommandException;
    boolean removeUser(String username) throws CommandException;
}
