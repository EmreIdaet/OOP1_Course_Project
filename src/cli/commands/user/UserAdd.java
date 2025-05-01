package cli.commands.user;

import exceptions.CommandException;
import interfaces.Command;
import interfaces.UserManager;

public class UserAdd implements Command {
    /**
     * This class is responsible for adding a new user to the system.
     * It checks if the user is an admin before allowing the addition of a new user.
     * If the user already exists, it informs the admin.
     */
    private final UserManager userManager;

    /**
     * Constructor for UserAdd command.
     *
     * @param userManager The user manager to handle user operations.
     */
    public UserAdd(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Executes the command to add a new user.
     * Checks if the user is an admin before allowing the addition of a new user.
     * If the user already exists, it informs the admin.
     *
     * @param args Command line arguments (username and password).
     * @throws CommandException If the user is not an admin or if there are any issues while adding the user.
     */
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
