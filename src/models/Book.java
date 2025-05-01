package models;

import enums.Genre;

import java.util.Arrays;
import java.util.List;

public class Book {
    /**
     * This class represents a book with an author, title, genre, description, year of publication,
     * keywords, rating, and ISBN number. It provides methods to get the book's details.
     */
    private Author author;
    private String title;
    private Genre genre;
    private String description;
    private int year;
    private List<String> keywords;
    private double rating;
    private int isbn;

    /**
     * Constructor to create a Book object with the specified author, title, genre, description,
     * year of publication, keywords, rating, and ISBN number.
     *
     * @param author      The author of the book.
     * @param title       The title of the book.
     * @param genre       The genre of the book.
     * @param description A brief description of the book.
     * @param year        The year of publication.
     * @param keywords    Keywords associated with the book.
     * @param rating      The rating of the book.
     * @param isbn        The ISBN number of the book.
     */
    public Book(Author author, String title, Genre genre, String description, int year, String keywords, double rating, int isbn) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.year = year;
        this.keywords = Arrays.asList(keywords.split(", "));
        this.rating = rating;
        this.isbn = isbn;
        this.author.getBooks().add(this);
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public int getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return this.title + " by " + this.author.getFirstName() + " " + this.author.getLastName() +
                "\nGenre: " + this.genre +
                "\nISBN: " + this.isbn;
    }
}
