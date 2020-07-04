package tictactoe;

import tictactoe.ai.Easy;
import tictactoe.ai.Player;
import tictactoe.engine.Game;
import tictactoe.engine.Board;

import static tictactoe.engine.State.PLAYING;

public final class Application {
    private Game game;

    public Application() {
        final var board = new Board();
        game = new Game(board, new Easy(board), new Player(board));
    }

    void run() {
        do {
            game.printBoard();
            game.nextMove();
        } while (game.getState() == PLAYING);

        game.printBoard();
        System.out.println(game.getState());
    }

}
