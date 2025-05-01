package exceptions;

public class CommandException extends Exception {
    /**
     * This class represents an exception that occurs during command execution.
     * It extends the Exception class to provide a custom exception for command-related errors.
     */
    public CommandException(String message) {
        super(message);
    }
}
