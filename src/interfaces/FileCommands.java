package interfaces;

public interface FileCommands {
    void loadFromFile(String filePath) throws Exception;
    void saveToFile(String filePath) throws Exception;
}
