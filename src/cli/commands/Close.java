package cli.commands;

import exceptions.CommandException;
import interfaces.BookManager;
import interfaces.Command;
import interfaces.FileStatus;
import interfaces.FileSupplier;
import manageres.BookMangerImpl;

import java.util.ArrayList;

public class Close implements Command {
    private final BookManager bookManager;
    private FileStatus fileStatus;
    private FileSupplier fileSupplier;

    public Close(BookManager bookManager, FileStatus fileStatus, FileSupplier fileSupplier) {
        this.bookManager = bookManager;
        this.fileStatus = fileStatus;
        this.fileSupplier = fileSupplier;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length < 1) {
            throw new CommandException("Usage: close");
        }
        ((BookMangerImpl) bookManager).setBooks(new ArrayList<>());
        String currentFile = fileSupplier.get();
        fileStatus.setCurrentFile(null);
        System.out.println("Successfully closed " + currentFile);
    }
}
