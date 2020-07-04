package tictactoe.engine;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class Board {
    private static final int SIZE = 3;
    private static final int[][] TRIPS = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    private final Random random = new Random();

    private Mark[] board = new Mark[9];

    public Board() {
        clean();
    }

    public void clean() {
        Arrays.fill(board, Mark.EMPTY);
    }

    public void set(int index, Mark state) {
        board[index] = state;
    }

    public void set(int x, int y, Mark state) {
        board[8 + x - 3 * y] = state;
    }

    public boolean isFree(int index) {
        return board[index] == Mark.EMPTY;
    }

    public boolean isFree(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IndexOutOfBoundsException();
        }
        return isFree(x - 3 * y + 8);
    }

    public IntStream getCells(Mark mark) {
        return IntStream.range(0, 9).filter(i -> board[i] == mark);
    }

    public Stream<int[]> getTrips(int i) {
        return Arrays.stream(TRIPS).filter(line -> line[0] == i || line[1] == i || line[2] == i);
    }

    public Optional<int[]> getTwoMarkTrips(Mark mark) {
        return Arrays.stream(TRIPS).filter(i ->
                board[i[0]] == Mark.EMPTY && board[i[1]] == mark && board[i[2]] == mark
                        || board[i[1]] == Mark.EMPTY && board[i[0]] == mark && board[i[2]] == mark
                        || board[i[2]] == Mark.EMPTY && board[i[1]] == mark && board[i[0]] == mark)
                .findFirst();
    }

    public int getRandomFree() {
        final var freeCells = range(0, 9).filter(this::isFree).toArray();
        return freeCells[random.nextInt(freeCells.length)];
    }

    public int getCellsCount(Mark symbol) {
        return (int) stream(board).filter(symbol::equals).count();
    }

    public int getTripsCount(Mark mark) {
        Predicate<int[]> threeInRow = line -> stream(line)
                .filter(i -> board[i] == mark)
                .count() == 3;

        return (int) stream(TRIPS).filter(threeInRow).count();
    }

    @Override
    public String toString() {
        return String.format(
                "---------%n| %c %c %c |%n| %c %c %c |%n| %c %c %c |%n---------",
                stream(board).map(Mark::getSymbol).toArray());
    }
}