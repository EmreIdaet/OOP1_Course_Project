package cli.commands;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.FileStatus;
import manageres.FileManager;
import models.Book;

import java.util.List;

public class SaveAs implements Command {
    private final BookManager bookManager;
    private final FileStatus fileStatus;

    public SaveAs(BookManager bookManager, FileStatus fileStatus) {
        this.bookManager = bookManager;
        this.fileStatus = fileStatus;
    }

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
            System.out.println("Successfully saved " + filePath);
        } catch (Exception e) {
            throw new CommandException("Failed to save as: " + e.getMessage());
        }
    }
}
