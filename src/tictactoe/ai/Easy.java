package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

import java.util.Random;
import java.util.logging.Logger;

public class Easy extends Ai {
    private final Random random;
    private static final Logger log = Logger.getLogger(Easy.class.getName());

    public Easy(Board board, Mark mark) {
        super(board, mark);
        this.random = new Random();
        log.info("Easy Ai created");
    }

    @Override
    public int getMove() {
        log.fine("Making move level Easy");
        System.out.println("Making move level Easy");
        return board.getRandomFree();
    }
}
