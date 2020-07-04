package tictactoe.ai;

import tictactoe.CellState;
import tictactoe.TicTacToeBoard;

public abstract class Ai {
    protected final TicTacToeBoard board;
    protected final CellState symbol;

    public Ai(TicTacToeBoard board, CellState symbol) {
        this.board = board;
        this.symbol = symbol;
    }

    public CellState getSymbol() {
        return symbol;
    }

    public abstract int getMove();
}
