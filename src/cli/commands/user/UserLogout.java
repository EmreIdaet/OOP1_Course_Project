package cli.commands.user;

import exceptions.CommandException;
import interfaces.Command;
import interfaces.UserManager;

public class UserLogout implements Command {
    /**
     * This class is responsible for handling user logout functionality.
     * It checks if the user is logged in and logs them out.
     */
    private final UserManager userManager;

    /**
     * Constructor for UserLogout command.
     *
     * @param userManager The user manager to handle user operations.
     */
    public UserLogout(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Executes the command to log out a user.
     * Checks if the user is logged in and logs them out.
     *
     * @param args Command line arguments (not used).
     * @throws CommandException If the user is not logged in or if there are any issues while logging out.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (!userManager.isLoggedIn()) {
            System.out.println("You are not logged in.");
            return;
        }
        userManager.logout();
        System.out.println("You have been logged out.");
    }
}
