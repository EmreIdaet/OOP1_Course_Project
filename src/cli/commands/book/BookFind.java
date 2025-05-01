package cli.commands.book;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import models.Book;

import java.util.List;

public class BookFind implements Command {
    /**
     * This class is responsible for finding books in the system based on a given option and value.
     * It checks if the user is logged in before performing the search.
     * If no books are found, it informs the user.
     */
    private final BookManager bookManager;
    private final UserManager userManager;

    /**
     * Constructor for BookFind command.
     *
     * @param bookManager The book manager to handle book operations.
     * @param userManager The user manager to check user permissions.
     */

    public BookFind(BookManager bookManager, UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

    /**
     * Executes the command to find books based on a given option and value.
     * Checks if the user is logged in before performing the search.
     *
     * @param args Command line arguments (option and value).
     * @throws CommandException If the user is not logged in or if there are no books found.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if(!userManager.isLoggedIn()) {
            throw new CommandException("You must be logged in to find a book.");
        }
        if (args.length < 2) {
            throw new CommandException("Usage: booksfind <option> <value>");
        }
        String option = args[1];
        String value = String.join(" ", java.util.Arrays.copyOfRange(args,2,args.length));
        if(value.isEmpty()) {
            throw new CommandException("Value cannot be empty. Usage: booksfind <option> <value> ");
        }
        List<Book> results = bookManager.findBooks(option, value);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }
}
