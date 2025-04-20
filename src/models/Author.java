package models;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private String firstName;
    private String lastName;
    private List<Book> books;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Author: ");
        sb.append(firstName).append(" ").append(lastName).append("\nBooks: ");
        for (Book book : books) {
            if(book.equals(books.getLast())) {
                sb.append(book.getTitle());
            } else {
                sb.append(book.getTitle()).append(", ");
            }
        }
        return sb.toString();
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
