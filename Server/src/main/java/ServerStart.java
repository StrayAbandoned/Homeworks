import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServerStart {
    private static LogManager logManager = LogManager.getLogManager();
    private static Logger logger = Logger.getLogger(ServerStart.class.getName());

    public static void main(String[] args) {

        try {

            logManager.readConfiguration(new FileInputStream("Server/logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Server();
    }

    public static Logger getLogger() {
        return logger;
    }
}
