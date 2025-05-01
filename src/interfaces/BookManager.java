package interfaces;

import exceptions.CommandException;
import models.Book;

import java.util.List;

public interface BookManager {
    /**
     * Adds a book to the book manager.
     *
     * @param book The book to be added.
     */
    void addBook(Book book);
    /**
     * Removes a book from the book manager.
     *
     * @param isbn The ISBN of the book to be removed.
     */
    void removeBook(int isbn);
    /**
     * Retrieves a book from the book manager.
     *
     * @param isbn The ISBN of the book to be retrieved.
     * @return The book with the specified ISBN.
     */
    Book getBook(int isbn);
    /**
     * Retrieves all books from the book manager.
     *
     * @return A list of all books.
     */
    List<Book> getAllBooks();
    /**
     * Finds books based on a specific option and value.
     *
     * @param option The option to search by (title, author, tag).
     * @param value  The value to search for.
     * @return A list of books that match the search criteria.
     */
    List<Book> findBooks(String option, String value);
    /**
     * Sorts the books based on a specified option and order.
     *
     * @param option The option to sort by (title, author, tag).
     * @param order  The order to sort in (asc or desc).
     * @throws CommandException If the sorting fails.
     */
    void sortBooks(String option, String order) throws CommandException;
}
