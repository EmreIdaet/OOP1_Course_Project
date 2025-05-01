package interfaces;

import exceptions.CommandException;

import java.io.IOException;

public interface Command {
    /**
     * Executes the command with the given arguments.
     *
     * @param args The command line arguments.
     * @throws CommandException If there is an error during command execution.
     * @throws IOException      If there is an error reading or writing to a file.
     */
    void execute(String[] args) throws CommandException, IOException;
}
