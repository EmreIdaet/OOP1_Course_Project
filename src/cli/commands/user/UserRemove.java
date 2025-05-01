package cli.commands.user;

import exceptions.CommandException;
import interfaces.Command;
import interfaces.UserManager;

public class UserRemove implements Command {
    /**
     * This class is responsible for removing a user from the system.
     * It checks if the user is an admin before allowing the removal of a user.
     * If the user does not exist, it informs the admin.
     */
    private final UserManager userManager;

    /**
     * Constructor for UserRemove command.
     *
     * @param userManager The user manager to handle user operations.
     */
    public UserRemove(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Executes the command to remove a user.
     * Checks if the user is an admin before allowing the removal of a user.
     * If the user does not exist, it informs the admin.
     *
     * @param args Command line arguments (username).
     * @throws CommandException If the user is not an admin or if there are any issues while removing the user.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (!userManager.isAdmin()) {
            throw new CommandException("Only admin can remove users.");
        }
        if (args.length < 2) {
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
