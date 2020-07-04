package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

public abstract class Ai {
    protected final Board board;
    protected final Mark mark;

    public Ai(Board board, Mark mark) {
        this.board = board;
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }

    public abstract int getMove();
}
