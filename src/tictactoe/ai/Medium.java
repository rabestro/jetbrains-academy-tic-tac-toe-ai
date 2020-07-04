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
        final var opponent = mark == Mark.X ? Mark.O : Mark.X;
        System.out.println("Making move level \"medium\"");

        return board.getTwoMarkTrips(getMark())
                .or(() -> board.getTwoMarkTrips(opponent))
                .map(line -> Arrays.stream(line).filter(board::isFree).findAny().getAsInt())
                .orElseGet(board::getRandomFree);
    }
}
