package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

import java.util.Random;

public class Easy extends Ai {
    private final Random random;

    public Easy(Board board, Mark mark) {
        super(board, mark);
        this.random = new Random();
    }

    @Override
    public int getMove() {
        System.out.println("Making move level Easy");
        return board.getRandomFree();
    }
}
