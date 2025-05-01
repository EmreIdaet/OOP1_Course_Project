package cli.commands;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.FileSupplier;
import manageres.FileManager;
import models.Book;

import java.util.List;

public class Save implements Command {
    /**
     * This class is responsible for saving the current state of the book manager to a file.
     * It retrieves the current file path from the file supplier and saves the books to that file.
     */
    private final BookManager bookManager;
    private final FileSupplier fileSupplier;

    /**
     * Constructor for Save command.
     *
     * @param bookManager  The book manager to handle book operations.
     * @param fileSupplier The file supplier to get the current file path.
     */
    public Save(BookManager bookManager, FileSupplier fileSupplier) {
        this.bookManager = bookManager;
        this.fileSupplier = fileSupplier;
    }

    /**
     * Executes the command to save the current state of the book manager to a file.
     * Retrieves the current file path from the file supplier and saves the books to that file.
     *
     * @param args Command line arguments (not used in this command).
     * @throws CommandException If there are any issues while saving the books.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        String currentFile = fileSupplier.getFile();
        String filename = currentFile.split("/")[currentFile.split("/").length - 1];
        if (filename == null || filename.isBlank()) {
            throw new CommandException("No file is currently opened.");
        }
        try {
            List<Book> books = bookManager.getAllBooks();
            FileManager.saveToFile(currentFile, books);
            System.out.println("Successfully saved " + filename);
        } catch (Exception e) {
            throw new CommandException("Failed to save: " + e.getMessage());
        }
    }
}
