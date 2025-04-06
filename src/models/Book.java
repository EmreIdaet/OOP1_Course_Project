package models;

import enums.Genre;

import java.util.Arrays;
import java.util.List;

public class Book {
    private Author author;
    private String title;
    private Genre genre;
    private String description;
    private int year;
    private List<String> keywords;
    private double rating;
    private int isbn;

    public Book(Author author, String title, Genre genre, String description, int year, String keywords, double rating, int isbn) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.year = year;
        this.keywords = Arrays.asList(keywords.split(", "));
        this.rating = rating;
        this.isbn = isbn;
    }
}
