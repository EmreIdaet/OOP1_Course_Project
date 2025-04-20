package cli.commands.user;

import exceptions.CommandException;
import interfaces.Command;
import interfaces.UserManager;

public class UserLogout implements Command {
    private final UserManager userManager;

    public UserLogout(UserManager userManager) {
        this.userManager = userManager;
    }

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
