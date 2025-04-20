package cli.commands.user;

import exceptions.CommandException;
import interfaces.Command;
import interfaces.UserManager;

public class UserRemove implements Command {
    private final UserManager userManager;

    public UserRemove(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (!userManager.isAdmin()) {
            throw new CommandException("Only admin can remove users.");
        }
        if (args.length < 1) {
            throw new CommandException("Usage: usersremove <username>");
        }
        String username = args[1];
        if (userManager.removeUser(username)) {
            System.out.println("User " + username + " removed successfully.");
        } else {
            System.out.println("User " + username + " does not exist.");
        }
    }
}
