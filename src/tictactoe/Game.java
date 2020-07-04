package tictactoe;

import tictactoe.ai.Ai;

import java.util.Arrays;

import static java.lang.Math.abs;

public class Game {
    private final TicTacToeBoard board;
    private final Ai[] players;

    public Game(TicTacToeBoard board, Ai playerX, Ai playerO) {
        this.board = board;
        this.players = new Ai[]{playerX, playerO};
    }

    public GameState getState() {
        final var moves = Arrays.stream(players)
                .map(Ai::getSymbol)
                .mapToInt(board::getCellsCount)
                .toArray();

        if (abs(moves[0] - moves[1]) > 1) {
            return GameState.IMPOSSIBLE;
        }
        return GameState.DRAW;
    }
}
