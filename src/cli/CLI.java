package cli;

import cli.commands.*;
import cli.commands.book.*;
import cli.commands.user.UserAdd;
import cli.commands.user.UserLogin;
import cli.commands.user.UserLogout;
import cli.commands.user.UserRemove;
import exceptions.CommandException;
import interfaces.*;
import manageres.BookMangerImpl;
import manageres.UserManagerImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private final BookManager bookManager = new BookMangerImpl();
    private final UserManager userManager = new UserManagerImpl();
    private final Scanner scanner = new Scanner(System.in);
    private String openedFile = null;
    private final Map<String, Command> commandMap = new HashMap<>();

    public CLI() {
        FileStatus fileStatus = this::setCurrentFile;
        FileSupplier fileSupplier = this::getCurrentFile;

        commandMap.put("open", new Open(bookManager, fileStatus));
        commandMap.put("close", new Close(bookManager, fileStatus, fileSupplier));
        commandMap.put("save", new Save(bookManager, fileSupplier));
        commandMap.put("saveas", new SaveAs(bookManager, fileStatus));
        commandMap.put("help", new Help());
        commandMap.put("exit", new Exit());
        commandMap.put("login", new UserLogin(userManager, scanner));
        commandMap.put("logout", new UserLogout(userManager));
        commandMap.put("booksadd", new BookAdd(bookManager, userManager, scanner));
        commandMap.put("booksremove", new BookRemove(bookManager, userManager));
        commandMap.put("booksall", new BookAll(bookManager, userManager));
        commandMap.put("booksfind", new BookFind(bookManager, userManager));
        commandMap.put("bookssort", new BookSort(bookManager, userManager));
        commandMap.put("booksinfo", new BookInfo(bookManager, userManager));
        commandMap.put("usersadd", new UserAdd(userManager));
        commandMap.put("usersremove", new UserRemove(userManager));
    }

    public void run(){
        System.out.println("Welcome to the Library System! Type 'help' for a list of commands.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] args = input.trim().split(" ");
            if (args.length == 0) {
                continue;
            }
            String commandName = args[0].toLowerCase();
            Command command = commandMap.get(commandName);
            if (command != null) {
                try {
                    command.execute(args);
                } catch (CommandException e) {
                    System.err.println(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Unknown command: " + commandName);
                System.out.println("Type 'help' for a list of commands.");
            }
        }
    }

    private void setCurrentFile(String file) {
        this.openedFile = file;
    }

    private String getCurrentFile() {
        return this.openedFile;
    }
}
