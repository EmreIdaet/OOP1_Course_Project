package cli.commands;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.FileStatus;
import interfaces.FileSupplier;
import manageres.BookMangerImpl;
import manageres.FileManager;
import models.Book;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Open implements Command {
    /**
     * This class is responsible for opening a file in the system.
     * It loads the books from the file and adds them to the book manager.
     * If the file does not exist, it creates a new one.
     */
    private final BookManager bookManager;
    private final FileStatus fileStatus;

    /**
     * Constructor for Open command.
     *
     * @param bookManager The book manager to handle book operations.
     * @param fileStatus  The file status to manage the current file.
     */
    public Open(BookManager bookManager, FileStatus fileStatus) {
        this.bookManager = bookManager;
        this.fileStatus = fileStatus;
    }

    /**
     * Executes the command to open a file.
     * Loads the books from the file and adds them to the book manager.
     * If the file does not exist, it creates a new one.
     *
     * @param args Command line arguments (file path).
     * @throws CommandException If there are any issues while opening the file.
     * @throws IOException      If there is an error reading or writing to the file.
     */
    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length < 2) {
            throw new CommandException("Usage: open <filename>");
        }

        String filePath = args[1];
        if (!filePath.endsWith(".txt")) {
            throw new CommandException("Invalid file format. Please use a .txt file.");
        }

        String filename = args[1].split("/")[args[1].split("/").length - 1];
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            List<Book> books = FileManager.loadFromFile(filePath);
            for (Book book : books) {
                bookManager.addBook(book);
            }
            fileStatus.setCurrentFile(filePath);
            System.out.println("Successfully opened " + filename);
        } catch (Exception e) {
            throw new CommandException("Failed to open file: " + e.getMessage());
        }
    }
}
