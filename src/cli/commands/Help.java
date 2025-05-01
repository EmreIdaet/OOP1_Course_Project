package cli.commands;

import exceptions.CommandException;
import interfaces.Command;

public class Help implements Command {
    /**
     * This class is responsible for displaying help information about the available commands.
     * It provides a list of commands and their descriptions to the user.
     */
    @Override
    public void execute(String[] args) throws CommandException {
        System.out.println("The following commands are supported:");
        System.out.println("booksadd                                - adds a book");
        System.out.println("booksremove <isbn>                      - removes a book");
        System.out.println("booksinfo <isbn>                        - shows information about a book");
        System.out.println("booksall                                - shows all books");
        System.out.println("booksfind <option> <value>      - searches for a book");
        System.out.println("bookssort <option> [asc | desc]         - updates a book");
        System.out.println("booksinfo <isbn_value>                  - updates a book");
        System.out.println("useradd <username> <password>           - adds a user");
        System.out.println("userremove <username>                   - removes a user");
        System.out.println("login                                   - logs in a user");
        System.out.println("logout                                  - logs out a user");
        System.out.println("open <file>                             - opens a file");
        System.out.println("close                                   - closes the current file");
        System.out.println("save                                    - saves to the opened file");
        System.out.println("saveas <file>                           - saves to another file");
        System.out.println("help                                    - prints this information");
        System.out.println("exit                                    - exits the program");
    }
}
