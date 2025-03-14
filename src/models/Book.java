package models;

public class Book {
    private Author author;
    private String title;
    private String genre;
    private String description;
    private int year;
    private String keywords;
    private double rating;
    private int isbn;

    public Book(Author author, String title, String genre, String description, int year, String keywords, double rating, int isbn) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.year = year;
        this.keywords = keywords;
        this.rating = rating;
        this.isbn = isbn;
    }
}
