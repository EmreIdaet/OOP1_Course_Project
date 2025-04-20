package cli.commands.book;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import manageres.FileManager;

public class BookRemove implements Command {
    private final BookManager bookManager;
    private final UserManager userManager;

    public BookRemove(BookManager bookManager, UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (!userManager.isAdmin()) {
            throw new CommandException("Only admin can remove books.");
        }
        if (args.length < 2) {
            throw new CommandException("Usage: booksremove <isbn>");
        }
        try {
            int isbn = Integer.parseInt(args[1]);
            if (bookManager.getBook(isbn) == null) {
                throw new CommandException("Book with ISBN " + isbn + " not found.");
            }
            bookManager.removeBook(isbn);
            try {
                FileManager.saveToFile("books.txt", bookManager.getAllBooks());
            } catch (Exception e) {
                System.out.println("Error removing book from file: " + e.getMessage());
            }
            System.out.println("Book with ISBN " + isbn + " removed successfully.");
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid ISBN format: " + e.getMessage());
        }
    }
}
