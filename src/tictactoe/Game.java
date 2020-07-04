package tictactoe;

import tictactoe.ai.Ai;

public class Game {
    private final TicTacToeBoard board;
    private final Ai[] players;

    public Game(TicTacToeBoard board, Ai playerX, Ai playerO) {
        this.board = board;
        this.players = new Ai[]{playerX, playerO};
    }

    public GameState getState() {
        return GameState.DRAW;
    }
}
