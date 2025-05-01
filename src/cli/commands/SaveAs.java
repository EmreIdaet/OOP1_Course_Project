package cli.commands;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.FileStatus;
import manageres.FileManager;
import models.Book;

import java.util.List;

public class SaveAs implements Command {
    /**
     * This class is responsible for saving the current state of the book manager to a new file.
     * It retrieves the current file path from the file status and saves the books to that file.
     */
    private final BookManager bookManager;
    private final FileStatus fileStatus;

    /**
     * Constructor for SaveAs command.
     *
     * @param bookManager The book manager to handle book operations.
     * @param fileStatus  The file status to manage the current file.
     */
    public SaveAs(BookManager bookManager, FileStatus fileStatus) {
        this.bookManager = bookManager;
        this.fileStatus = fileStatus;
    }

    /**
     * Executes the command to save the current state of the book manager to a new file.
     * Retrieves the current file path from the file status and saves the books to that file.
     *
     * @param args Command line arguments (file path).
     * @throws CommandException If there are any issues while saving the books.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if(args.length != 2) {
            throw new CommandException("Usage: saveas <file>");
        }
        if(args[1].isEmpty()) {
            throw new CommandException("File path cannot be empty.");
        }
        String filePath = args[1];
        try {
            List<Book> books = bookManager.getAllBooks();
            if (books.isEmpty()) {
                throw new CommandException("No books to save.");
            }
            FileManager.saveToFile(filePath, books);
            fileStatus.setCurrentFile(filePath);
            String filename = args[1].split("/")[args[1].split("/").length - 1];
            System.out.println("Successfully saved " + filename);
        } catch (Exception e) {
            throw new CommandException("Failed to save as: " + e.getMessage());
        }
    }
}
