package interfaces;

import exceptions.CommandException;

import java.io.IOException;

public interface Command {
    void execute(String[] args) throws CommandException, IOException;
}
