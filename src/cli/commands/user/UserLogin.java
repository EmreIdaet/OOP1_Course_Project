package cli.commands.user;

import exceptions.CommandException;
import interfaces.Command;
import interfaces.UserManager;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserLogin implements Command {
    private final UserManager userManager;
    //private final Scanner scanner;
    private final BufferedReader reader;

    public UserLogin(UserManager userManager, Scanner scanner) {
        this.userManager = userManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if(userManager.isLoggedIn()){
            throw new CommandException("You are already logged in.");
        }

        try {
        System.out.print("Username: ");
        String username = reader.readLine();
        String password = readPassword("Password: ");
        userManager.login(username, password);
        System.out.println("Welcome, " + username + "!");
        } catch (CommandException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String readPassword(String prompt) throws IOException {
        Console console = System.console();
        if (console != null){
            char[] passwordArray = console.readPassword(prompt);
            return new String(passwordArray);
        }
        System.out.print(prompt);
        StringBuilder password = new StringBuilder();
        while (true) {
            int ch = System.in.read();
            if (ch == '\n' || ch == '\r') break;
            if (ch == 127 || ch == 8) { // backspace
                if (password.length() > 0) {
                    password.deleteCharAt(password.length() - 1);
                    System.out.print("\b \b");
                }
            } else {
                password.append((char) ch);
                System.out.print("*");
            }
        }
        System.out.println();
        return password.toString();
    }
}
