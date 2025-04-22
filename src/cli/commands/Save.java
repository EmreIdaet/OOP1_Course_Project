package cli.commands;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.FileSupplier;
import manageres.FileManager;
import models.Book;

import java.util.List;

public class Save implements Command {
    private final BookManager bookManager;
    private final FileSupplier fileSupplier;

    public Save(BookManager bookManager, FileSupplier fileSupplier) {
        this.bookManager = bookManager;
        this.fileSupplier = fileSupplier;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        String currentFile = fileSupplier.get();
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
