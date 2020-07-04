package tictactoe;

import tictactoe.ai.Ai;
import tictactoe.ai.Easy;
import tictactoe.ai.Player;

public final class Application {
    private TicTacToeBoard board;
    private Ai ai;
    private Ai player;
    private Game game;

    public Application() {
        board = new TicTacToeBoard();
        ai = new Easy(board);
        player = new Player(board);

        game = new Game(board, ai, player);
    }

    void run() {
        System.out.println(board);
        board.set(player.getMove(), player.getSymbol());
        var isPlaying = game.getState() == GameState.PLAYING;

        System.out.println(board);

        board.set(ai.getMove(), CellState.O);
        System.out.println(board);
    }

}
