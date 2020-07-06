package tictactoe.ai;

import tictactoe.engine.Board;
import tictactoe.engine.Mark;

import java.util.Arrays;

public class Medium extends Ai {
    public Medium(Board board, Mark mark) {
        super(board, mark);
    }

    @Override
    public int getMove() {
        System.out.println("Making move level Medium");
        final var opponent = mark == Mark.X ? Mark.O : Mark.X;

        return board.getTwoMarkTrips(getMark())
                .or(() -> board.getTwoMarkTrips(opponent))
                .map(line -> Arrays.stream(line).filter(board::isEmpty).findAny().getAsInt())
                .orElseGet(board::getRandomFree);
    }
}
