package tictactoe.engine;

import tictactoe.ai.Ai;
import tictactoe.ai.Player;
import static tictactoe.engine.State.PLAYING;

public class Game {
    private final Board board;
    private final Ai[] players;
    private int currentPlayer;

    public Game(Board board, Player playerX, Player playerO) {
        this.board = board;
        this.players = new Ai[]{
                playerX.create(board, Mark.X),
                playerO.create(board, Mark.O)};
        currentPlayer = 0;
    }

    public void start() {
        do {
            System.out.println(board);
            nextMove();
        } while (board.getGameState() == PLAYING);

        System.out.println(board);
        System.out.println(board.getGameState().getMessage());
    }

    public void nextMove() {
        final var player = players[currentPlayer];
        board.set(player.getMove(), player.getMark());
        currentPlayer = 1 - currentPlayer;
    }
}
