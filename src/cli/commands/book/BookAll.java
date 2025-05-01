package cli.commands.book;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import models.Book;

public class BookAll implements Command {
    /**
     * This class is responsible for displaying all books in the system.
     * It checks if the user is logged in before displaying the books.
     * If no books are found, it informs the user.
     */
    private final BookManager bookManager;
    private final UserManager userManager;

    /**
     * Constructor for BookAll command.
     *
     * @param bookManager The book manager to handle book operations.
     * @param userManager The user manager to check user permissions.
     */
    public BookAll(BookManager bookManager , UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

    /**
     * Executes the command to display all books.
     * Checks if the user is logged in before displaying the books.
     *
     * @param args Command line arguments (not used in this command).
     * @throws CommandException If the user is not logged in or if there are no books available.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (!userManager.isLoggedIn()) {
            throw new CommandException("You must be logged in to view all books.");
        }
        if (bookManager.getAllBooks().isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : bookManager.getAllBooks()) {
            System.out.println(book);
        }
    }
}
