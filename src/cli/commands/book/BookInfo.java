package cli.commands.book;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import models.Book;

import java.io.IOException;

public class BookInfo implements Command {
    /**
     * This class is responsible for displaying information about a specific book.
     * It checks if the user is logged in before displaying the book information.
     * If the book is not found, it informs the user.
     */
    private final BookManager bookManager;
    private final UserManager userManager;

    /**
     * Constructor for BookInfo command.
     *
     * @param bookManager The book manager to handle book operations.
     * @param userManager The user manager to check user permissions.
     */
    public BookInfo(BookManager bookManager, UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

    /**
     * Executes the command to display information about a specific book.
     * Checks if the user is logged in before displaying the book information.
     *
     * @param args Command line arguments (ISBN of the book).
     * @throws CommandException If the user is not logged in or if the book is not found.
     * @throws IOException      If there is an error retrieving the book information.
     */
    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (!userManager.isLoggedIn()) {
            throw new CommandException("You must be logged in to view book information.");
        }
        if (args.length < 2) {
            throw new CommandException("Usage: booksinfo <isbn>");
        }
        int isbn = Integer.parseInt(args[1]);
        Book book = bookManager.getBook(isbn);
        if (book == null) {
            throw new CommandException("Book " + isbn + " not found.");
        }
        System.out.println(book);
    }
}
