package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

public abstract class Ai {
    protected final Board board;
    protected final Mark symbol;

    public Ai(Board board, Mark mark) {
        this.board = board;
        this.symbol = mark;
    }

    public Mark getSymbol() {
        return symbol;
    }

    public abstract int getMove();
}
