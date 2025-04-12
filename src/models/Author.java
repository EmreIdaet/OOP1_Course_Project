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
        return "Author: " + firstName + " " + lastName + "\nBooks: " + books;
    }
}
