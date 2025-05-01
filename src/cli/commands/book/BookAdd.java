package cli.commands.book;

import enums.Genre;
import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.UserManager;
import models.Author;
import models.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookAdd implements Command {
    /**
     * This class is responsible for adding a new book to the system.
     * It checks if the user is an admin before allowing the addition of a book.
     * If the user is not an admin, it throws a CommandException.
     */
    private final BookManager bookManager;
    private final UserManager userManager;
    private Scanner scanner;

    /**
     * Constructor for BookAdd command.
     *
     * @param bookManager The book manager to handle book operations.
     * @param userManager The user manager to check user permissions.
     * @param scanner     Scanner for user input.
     */
    public BookAdd(BookManager bookManager, UserManager userManager, Scanner scanner) {
        this.bookManager = bookManager;
        this.userManager = userManager;
        this.scanner = scanner;
    }

    /**
     * Executes the command to add a new book.
     * Prompts the user for book details and adds the book to the library.
     * Only admin users can execute this command.
     *
     * @param args Command line arguments (not used in this command).
     * @throws CommandException If the user is not an admin or if there is an error adding the book.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (!userManager.isAdmin()) {
            throw new CommandException("Only admin can add books.");
        }

        try {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author first name: ");
            String first = scanner.nextLine();
            System.out.print("Author last name: ");
            String last = scanner.nextLine();
            System.out.print("Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Rating: ");
            double rating = Double.parseDouble(scanner.nextLine());
            System.out.print("Genre: ");
            Genre genre = Genre.fromString(scanner.nextLine());
            System.out.print("Keywords (comma-separated as \"keyword1, keyword2\"): ");
            String keywordsInput = scanner.nextLine();
            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.print("ISBN (int): ");
            int isbn = Integer.parseInt(scanner.nextLine());

            Book book = new Book(new Author(first, last), title, genre, description, year, keywordsInput, rating, isbn);
            bookManager.addBook(book);
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            throw new CommandException("Book not added: " + e.getMessage());
        } catch (Exception e) {
            throw new CommandException("Invalid input: " + e.getMessage());
        }
    }
}
