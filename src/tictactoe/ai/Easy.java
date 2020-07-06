package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

public class Easy extends Ai {

    public Easy(Board board, Mark mark) {
        super(board, mark);
    }

    @Override
    public int getMove() {
        System.out.println("Making move level Easy");
        return board.getRandomFree();
    }
}
