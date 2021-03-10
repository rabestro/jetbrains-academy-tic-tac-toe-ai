package tictactoe;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public final class Main {
    static {
        try {
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }

    public static void main(String[] args) {
        new Application().run();
    }
}


