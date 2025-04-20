package cli.commands.user;

import exceptions.CommandException;
import interfaces.Command;
import interfaces.UserManager;

public class UserAdd implements Command {
    private final UserManager userManager;

    public UserAdd(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (!userManager.isAdmin()) {
            throw new CommandException("Only admin can add users.");
        }
        if (args.length < 2) {
            throw new CommandException("Usage: usersadd <username> <password>");
        }
        String username = args[1];
        String password = args[2];
        if (userManager.addUser(username, password)) {
            System.out.println("User " + username + " added successfully.");
        } else {
            System.out.println("User " + username + " already exists.");
        }
    }
}
