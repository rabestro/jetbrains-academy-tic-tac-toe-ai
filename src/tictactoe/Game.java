package tictactoe;

import tictactoe.ai.Ai;

import static java.lang.Math.abs;
import static java.util.Arrays.stream;

public class Game {
    private final TicTacToeBoard board;
    private final Ai[] players;
    private GameState state;
    private int currentPlayer;

    public Game(TicTacToeBoard board, Ai playerX, Ai playerO) {
        this.board = board;
        this.players = new Ai[]{playerX, playerO};
        state = GameState.PLAYING;
        currentPlayer = 0;
    }

    public GameState getState() {
        return state;
    }

    private void checkState() {
        final var moves = stream(players)
                .map(Ai::getSymbol)
                .mapToInt(board::getCellsCount)
                .toArray();

        final var trips = stream(players)
                .map(Ai::getSymbol)
                .mapToInt(board::getTripsCount)
                .toArray();

        if (abs(moves[0] - moves[1]) > 1 || trips[0] + trips[1] > 1) {
            state = GameState.IMPOSSIBLE;
        } else if (trips[0] == 1) {
            state = GameState.X_WINS;
        } else if (trips[1] == 1) {
            state = GameState.O_WINS;
        } else if (moves[0] + moves[1] == 9) {
            state = GameState.DRAW;
        } else {
            state = GameState.PLAYING;
        }
    }

    public void printBoard() {
        System.out.println(board);
    }

    public void nextMove() {
        final var player = players[currentPlayer];
        board.set(player.getMove(), player.getSymbol());
        currentPlayer = 1 - currentPlayer;
        checkState();
    }
}
