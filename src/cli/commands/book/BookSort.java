package cli.commands.book;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import models.Book;

public class BookSort implements Command {
    /**
     * This class is responsible for sorting books based on a specified option.
     * It checks if the user is logged in before sorting the books.
     * If no books are found, it informs the user.
     */
    private final BookManager bookManager;
    private final UserManager userManager;

    /**
     * Constructor for BookSort command.
     *
     * @param bookManager The book manager to handle book operations.
     * @param userManager The user manager to check user permissions.
     */
    public BookSort(BookManager bookManager, UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

    /**
     * Executes the command to sort books based on a specified option.
     * Checks if the user is logged in before sorting the books.
     *
     * @param args Command line arguments (option and order).
     * @throws CommandException If the user is not logged in or if there are no books available.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (!userManager.isLoggedIn()) {
            throw new CommandException("You must be logged in to sort books.");
        }
        if (args.length < 1) {
            throw new CommandException("Usage: bookssort <option> [asc | desc] ");
        }
        String option = args[1];
        boolean ascending = args.length < 3 || args[2].equalsIgnoreCase("asc");
        bookManager.sortBooks(option, ascending?"asc":"desc");

        for (Book book : bookManager.getAllBooks()) {
            System.out.println(book);
        }
    }
}
