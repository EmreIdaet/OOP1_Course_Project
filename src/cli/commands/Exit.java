package cli.commands;

import interfaces.Command;

public class Exit implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting the program... Goodbye!");
        System.exit(0);
    }
}
