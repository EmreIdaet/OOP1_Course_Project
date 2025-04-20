package cli.commands.book;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import models.Book;

public class BookAll implements Command {
    private final BookManager bookManager;
    private final UserManager userManager;

    public BookAll(BookManager bookManager , UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

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
