package tictactoe;

import java.util.Arrays;
import java.util.stream.Stream;

public class TicTacToeBoard {
    private static final int SIZE = 3;
    private CellState[] board = new CellState[9];

    public TicTacToeBoard() {
        clean();
    }

    public void clean() {
        Arrays.fill(board, CellState.EMPTY);
    }

    public void set(int index, CellState state) {
        board[index] = state;
    }

    public void set(int x, int y, CellState state) {
        board[8 + x - 3 * y] = state;
    }

    public boolean isFree(int index) {
        return board[index] == CellState.EMPTY;
    }

    public boolean isFree(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IndexOutOfBoundsException();
        }
        return isFree(x - 3 * y + 8);
    }

    public int getCellsCount(CellState state) {
        return (int) Arrays.stream(board).filter(state::equals).count();
    }

    @Override
    public String toString() {
        return String.format(
                "---------%n| %c %c %c |%n| %c %c %c |%n| %c %c %c |%n---------",
                Arrays.stream(board).map(e -> e.symbol).toArray());
    }
}