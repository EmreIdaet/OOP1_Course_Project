package cli.commands;

import interfaces.Command;

public class Exit implements Command {
    /**
     * This class is responsible for exiting the program.
     * It prints a goodbye message and terminates the program.
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting the program... Goodbye!");
        System.exit(0);
    }
}
