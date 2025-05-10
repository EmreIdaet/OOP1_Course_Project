package manageres;

import exceptions.CommandException;
import interfaces.BookManager;
import models.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LibraryManger implements BookManager {
    /**
     * This class implements the BookManager interface and provides methods to manage a collection of books.
     * It allows adding, removing, retrieving, and sorting books based on various criteria.
     */
    private static List<Book> books = new ArrayList<>();

    /**
     * This method sets the list of books.
     *
     * @param books The list of books to be set.
     */
    public static void setBooks(List<Book> books) {
        LibraryManger.books = books;
    }

    /**
     * This method retrieves the list of books.
     *
     * @return The list of books.
     */
    @Override
    public void addBook(Book book) {
        if(getBook(book.getIsbn()) != null) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.add(book);
    }

    /**
     * This method removes a book from the list based on its ISBN.
     *
     * @param isbn The ISBN of the book to be removed.
     */
    @Override
    public void removeBook(int isbn) {
        books.removeIf(book -> book.getIsbn() == isbn);
    }

    /**
     * This method retrieves a book from the list based on its ISBN.
     *
     * @param isbn The ISBN of the book to be retrieved.
     * @return The book with the specified ISBN.
     */
    @Override
    public Book getBook(int isbn) {
        for (Book book : books) {
            if (book.getIsbn() == isbn) {
                return book;
            }
        }
        return null;
    }

    /**
     * This method retrieves all books from the list.
     *
     * @return A list of all books.
     */
    @Override
    public List<Book> getAllBooks() {
//        if (books.isEmpty()) {
//            System.out.println("No books available.");
//        }
        return new ArrayList<>(books);
    }

    /**
     * This method finds books based on a specific option and value.
     *
     * @param option The option to search by (title, author, tag).
     * @param value  The value to search for.
     * @return A list of books that match the search criteria.
     */
    @Override
    public List<Book> findBooks(String option, String value) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            switch (option) {
                case "title":
                    if (book.getTitle().equalsIgnoreCase(value)) {
                        foundBooks.add(book);
                    }
                    break;
                case "author":
                    String authorName = book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName();
                    if (authorName.equalsIgnoreCase(value)) {
                        foundBooks.add(book);
                    }
                    break;
                case "tag":
                    for (String keyword : book.getKeywords()) {
                        if (keyword.equalsIgnoreCase(value)) {
                            foundBooks.add(book);
                            break;
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid search option: " + option);
            }
//            if (option.equals("title") && book.getTitle().equalsIgnoreCase(value)) {
//                foundBooks.add(book);
//            } else if (option.equals("author") && (book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName()).equalsIgnoreCase(value)) {
//                foundBooks.add(book);
//            } else if (option.equals("tag")) {
//                for (String keyword : book.getKeywords()) {
//                    if (keyword.equalsIgnoreCase(value)) {
//                        foundBooks.add(book);
//                    }
//                }
//            }
        }
        return new ArrayList<>(foundBooks);
    }

    /**
     * This method sorts the books based on a specified option and order.
     *
     * @param option The option to sort by (title, author, tag).
     * @param order  The order to sort in (asc or desc).
     * @throws CommandException If the sorting fails.
     */
    @Override
    public void sortBooks(String option, String order) throws CommandException {
        Comparator<Book> comparator = null;
        switch (option) {
            case "title":
                comparator = Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER);
                break;
            case "author":
                comparator = Comparator.comparing(book -> book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName(), String.CASE_INSENSITIVE_ORDER);
                break;
            case "year":
                comparator = Comparator.comparingInt(Book::getYear);
                break;
            case "rating":
                comparator = Comparator.comparingDouble(Book::getRating);
                break;
            default:
                throw new CommandException("Invalid sort option: " + option);
        }
        books = quickSort(books, comparator);
        if (order.equals("desc")) {
            books = books.reversed();
        }

    }

    /**
     * This method sorts the books using the quicksort algorithm.
     *
     * @param books      The list of books to be sorted.
     * @param comparator The comparator to determine the order of sorting.
     * @return A sorted list of books.
     */
    private List<Book> quickSort(List<Book> books, Comparator<Book> comparator) {
        if (books.size() <= 1) {
            return books;
        }
        Book pivot = books.get(books.size() / 2);
        List<Book> less = new ArrayList<>();
        List<Book> equal = new ArrayList<>();
        List<Book> greater = new ArrayList<>();
        for (Book book : books) {
            if (comparator.compare(book, pivot) < 0) {
                less.add(book);
            } else if (comparator.compare(book, pivot) > 0) {
                greater.add(book);
            } else{
                equal.add(book);
            }

        }
        List<Book> sortedBooks = quickSort(less, comparator);
        sortedBooks.addAll(equal);
        sortedBooks.addAll(quickSort(greater, comparator));
        return sortedBooks;
    }
}
