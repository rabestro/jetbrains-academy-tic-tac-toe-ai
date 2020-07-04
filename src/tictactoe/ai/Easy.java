package tictactoe.ai;

import tictactoe.CellState;
import tictactoe.TicTacToeBoard;

import java.util.Random;

import static java.util.stream.IntStream.range;

public class Easy extends Ai {
    private final Random random;

    public Easy(TicTacToeBoard board) {
        super(board, CellState.O);
        this.random = new Random();
    }

    @Override
    public int getMove() {
        System.out.println("Making move level \"easy\"");
        final var freeCells = range(0, 9).filter(board::isFree).toArray();
        return freeCells[random.nextInt(freeCells.length)];
    }
}
