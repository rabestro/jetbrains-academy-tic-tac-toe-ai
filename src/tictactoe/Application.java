package tictactoe;

import tictactoe.ai.*;
import tictactoe.engine.Game;
import tictactoe.engine.Board;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class Application implements Runnable {
    private static final Pattern PATTERN_COMMAND = Pattern.compile("(exit|start( easy| medium| hard| user){2})");
    private static final Map<String, Player> players = Map.of("user", User::new,
            "easy", Easy::new, "medium", Medium::new, "hard", Hard::new);

    public void run() {
        do {
            final var args = readCommand().split(" ");

            if (args[0].equals("exit")) {
                break;
            }
            new Game(
                    new Board(),
                    players.get(args[1]),
                    players.get(args[2])
            ).start();

        } while (true);
    }

    private String readCommand() {
        final var scanner = new Scanner(System.in);
        do {
            System.out.println("Input command: ");
            final var command = scanner.nextLine().toLowerCase();
            if (PATTERN_COMMAND.matcher(command).matches()) {
                return command;
            }
            System.out.println("Bad parameters!");
        } while (true);
    }
}
