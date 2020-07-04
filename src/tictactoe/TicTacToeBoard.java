package tictactoe;

import java.util.Arrays;

public class TicTacToeBoard {
    private static final int SIZE = 3;

    private CellState[] board = new CellState[9];

    public TicTacToeBoard() {
        Arrays.fill(board, CellState.EMPTY);
    }

    public void clean() {
        Arrays.fill(board, CellState.EMPTY);
    }

    void set(int index, CellState state) {
        board[index] = state;
    }

    boolean isFree(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IndexOutOfBoundsException();
        }
        return isFree(x - 3 * y + 8);
    }

    boolean isFree(int index) {
        return board[index] == CellState.EMPTY;
    }

    @Override
    public String toString() {
        return String.format(
                "---------%n" + "| %c %c %c |%n".repeat(3) + "---------",
                Arrays.stream(board).map(e -> e.symbol).toArray());
    }
}