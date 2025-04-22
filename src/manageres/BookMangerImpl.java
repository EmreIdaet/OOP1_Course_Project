package manageres;

import interfaces.BookManager;
import interfaces.FileSupplier;
import models.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookMangerImpl implements BookManager {
    private static List<Book> books = new ArrayList<>();

    public static void setBooks(List<Book> books) {
        BookMangerImpl.books = books;
    }

    @Override
    public void addBook(Book book) {
        if(getBook(book.getIsbn()) != null) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.add(book);
    }

    @Override
    public void removeBook(int isbn) {
        books.removeIf(book -> book.getIsbn() == isbn);
    }

    @Override
    public Book getBook(int isbn) {
        for (Book book : books) {
            if (book.getIsbn() == isbn) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
//        if (books.isEmpty()) {
//            System.out.println("No books available.");
//        }
        return new ArrayList<>(books);
    }

    @Override
    public List<Book> findBooks(String option, String value) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (option.equals("title") && book.getTitle().equalsIgnoreCase(value)) {
                foundBooks.add(book);
            } else if (option.equals("author") && (book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName()).equalsIgnoreCase(value)) {
                foundBooks.add(book);
            } else if (option.equals("tag")) {
                for (String keyword : book.getKeywords()) {
                    if (keyword.equalsIgnoreCase(value)) {
                        foundBooks.add(book);
                    }
                }
            }
        }
        return new ArrayList<>(foundBooks);
    }

    @Override
    public void sortBooks(String option, String order) {
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
                System.out.println("Invalid sort option.");
                return;
        }
        books = quickSort(books, comparator);
        if (order.equals("desc")) {
            books = books.reversed();
        }

    }

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
