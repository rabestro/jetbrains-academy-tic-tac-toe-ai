package tictactoe.ai;

import tictactoe.engine.Board;
import tictactoe.engine.Mark;

public class Medium extends Ai {
    public Medium(Board board, Mark mark) {
        super(board, mark);
    }

    @Override
    public int getMove() {
        return 0;
    }
}
