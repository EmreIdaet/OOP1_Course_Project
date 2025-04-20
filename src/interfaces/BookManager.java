package interfaces;

import models.Book;

import java.util.List;

public interface BookManager {
    void addBook(Book book);
    void removeBook(int isbn);
    Book getBook(int isbn);
    List<Book> getAllBooks();
    List<Book> findBooks(String option, String value);
    void sortBooks(String option, String order);
}
