package cli.commands.book;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import models.Book;

import java.io.IOException;

public class BookInfo implements Command {
    private final BookManager bookManager;
    private final UserManager userManager;

    public BookInfo(BookManager bookManager, UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (!userManager.isLoggedIn()) {
            throw new CommandException("You must be logged in to view book information.");
        }
        if (args.length < 2) {
            throw new CommandException("Usage: bookinfo <isbn>");
        }
        int isbn = Integer.parseInt(args[1]);
        Book book = bookManager.getBook(isbn);
        if (book == null) {
            throw new CommandException("Book " + isbn + " not found.");
        }
        System.out.println(book);
    }
}
