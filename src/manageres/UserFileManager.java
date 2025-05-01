package manageres;

import models.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserFileManager {
    /**
     * This class manages user data by loading and saving it to a file.
     * It provides methods to load users from a file and save users to a file.
     */
    private final String filename;

    /**
     * Constructor to initialize the UserFileManager with a specific filename.
     *
     * @param filename The name of the file to load and save user data.
     */
    public UserFileManager(String filename) {
        this.filename = filename;
    }

    /**
     * Loads users from the specified file.
     *
     * @return A map of usernames to User objects.
     * @throws IOException If there is an error reading the file.
     */
    public Map<String, User> loadUsers() throws IOException {
        Map<String, User> users = new HashMap<>();
        File file = new File(filename);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    boolean isAdmin = Boolean.parseBoolean(parts[2]);
                    users.put(username.toLowerCase(), new User(username, password, isAdmin));
                }
            }
        }
        return users;
    }

    /**
     * Saves the given users to the specified file.
     *
     * @param users A map of usernames to User objects.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveUsers(Map<String, User> users) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : users.values()) {
                String line = String.join("|",
                        user.getUsername(),
                        user.getPassword(),
                        String.valueOf(user.isAdmin()));
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
