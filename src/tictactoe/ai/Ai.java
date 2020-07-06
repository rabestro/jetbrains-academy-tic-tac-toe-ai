package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

import java.util.Random;
import java.util.logging.Logger;

public abstract class Ai {
    protected static final Logger log = Logger.getLogger(Ai.class.getName());
    protected final Random random = new Random();
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
