package tictactoe.ai;

import tictactoe.TicTacToeBoard;

import java.util.Random;

import static java.util.stream.IntStream.range;

public class Easy implements Ai {
    private final Random random;

    public Easy() {
        this.random = new Random();
    }

    @Override
    public int getMove(TicTacToeBoard board) {
        final var freeCells = range(0, 9).filter(board::isFree).toArray();
        return freeCells[random.nextInt(freeCells.length)];
    }
}
