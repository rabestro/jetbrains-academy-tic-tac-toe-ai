package tictactoe;

import tictactoe.ai.Easy;
import tictactoe.ai.Player;

import static tictactoe.GameState.PLAYING;

public final class Application {
    private Game game;

    public Application() {
        final var board = new TicTacToeBoard();
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
