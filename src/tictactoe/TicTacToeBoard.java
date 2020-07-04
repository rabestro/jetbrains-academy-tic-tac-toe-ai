package tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;

import static java.util.Arrays.stream;

public class TicTacToeBoard {
    private static final int SIZE = 3;
    private static final int[][] TRIPS = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

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

    public int getCellsCount(CellState symbol) {
        return (int) stream(board).filter(symbol::equals).count();
    }

    public int getTripsCount(CellState mark) {
        Predicate<int[]> threeInRow = line -> stream(line)
                .filter(i -> board[i] == mark)
                .count() == 3;

        return (int) stream(TRIPS).filter(threeInRow).count();
    }

    @Override
    public String toString() {
        return String.format(
                "---------%n| %c %c %c |%n| %c %c %c |%n| %c %c %c |%n---------",
                stream(board).map(CellState::getSymbol).toArray());
    }
}