package cli.commands.book;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import models.Book;

public class BookSort implements Command {
    private final BookManager bookManager;
    private final UserManager userManager;

    public BookSort(BookManager bookManager, UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

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
