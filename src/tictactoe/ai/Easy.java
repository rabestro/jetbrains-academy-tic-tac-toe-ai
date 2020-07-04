package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

import java.util.Random;

import static java.util.stream.IntStream.range;

public class Easy extends Ai {
    private final Random random;

    public Easy(Board board, Mark mark) {
        super(board, mark);
        this.random = new Random();
    }

    @Override
    public int getMove() {
        System.out.println("Making move level \"easy\"");
        return board.getRandomFree();
    }
}
