package manageres;

import enums.Genre;
import models.Author;
import models.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<Book> loadFromFile(String filePath) throws Exception {
        File file = new File(filePath);
        List<Book> books = new ArrayList<>();
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            //J.R.R. Tolkien|The Hobbit|FANTASY|description|1937|adventure,dragon,journey|4.8|123456
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 8) {
                    throw new IllegalArgumentException("Invalid file format");
                }
                String authorName = parts[0];
                String[] authorParts = authorName.split(" ");
                Author author = new Author(authorParts[0], authorParts[1]);
                String title = parts[1];
                String genre = parts[2];
                String description = parts[3];
                int year = Integer.parseInt(parts[4]);
                String keywords = parts[5];
                double rating = Double.parseDouble(parts[6]);
                int isbn = Integer.parseInt(parts[7]);

                books.add(new Book(author, title, Genre.fromString(genre), description, year, keywords, rating, isbn));
            }
        }
        return books;
    }

    public static void saveToFile(String filePath, List<Book> books) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Book book : books) {
                String line = book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() + "|" +
                        book.getTitle() + "|" +
                        book.getGenre() + "|" +
                        book.getDescription() + "|" +
                        book.getYear() + "|" +
                        String.join(", ", book.getKeywords()) + "|" +
                        book.getRating() + "|" +
                        book.getIsbn();
                writer.write(line);
                writer.newLine();
            }
        }

    }
}