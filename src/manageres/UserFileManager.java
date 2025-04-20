package manageres;

import models.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserFileManager {
    private final String filename;

    public UserFileManager(String filename) {
        this.filename = filename;
    }

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
