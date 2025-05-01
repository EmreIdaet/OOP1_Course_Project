package cli.commands;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.FileStatus;
import interfaces.FileSupplier;
import manageres.BookMangerImpl;

import java.util.ArrayList;

public class Close implements Command {
    /**
     * This class is responsible for closing the current file in the system.
     * It clears the list of books and sets the current file to null.
     */
    private final BookManager bookManager;
    private FileStatus fileStatus;
    private FileSupplier fileSupplier;

    /**
     * Constructor for Close command.
     *
     * @param bookManager The book manager to handle book operations.
     * @param fileStatus  The file status to manage the current file.
     * @param fileSupplier The file supplier to get the current file.
     */
    public Close(BookManager bookManager, FileStatus fileStatus, FileSupplier fileSupplier) {
        this.bookManager = bookManager;
        this.fileStatus = fileStatus;
        this.fileSupplier = fileSupplier;
    }

    /**
     * Executes the command to close the current file.
     * Clears the list of books and sets the current file to null.
     *
     * @param args Command line arguments (not used in this command).
     * @throws CommandException If there are any issues while closing the file.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length < 1) {
            throw new CommandException("Usage: close");
        }
        BookMangerImpl.setBooks(new ArrayList<>());
        String currentFile = fileSupplier.getFile();
        String filename = currentFile.split("/")[currentFile.split("/").length - 1];
        fileStatus.setCurrentFile(null);
        System.out.println("Successfully closed " + filename);
    }
}
