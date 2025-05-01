package models;

import java.util.ArrayList;
import java.util.List;

public class Author {
    /**
     * This class represents an author with a first name, last name, and a list of books.
     * It provides methods to get the author's details and the list of books they have written.
     */
    private String firstName;
    private String lastName;
    private List<Book> books;

    /**
     * Constructor to create an Author object with the specified first name and last name.
     *
     * @param firstName The first name of the author.
     * @param lastName  The last name of the author.
     */
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
