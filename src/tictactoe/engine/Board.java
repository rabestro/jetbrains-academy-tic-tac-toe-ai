package tictactoe.engine;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.Math.abs;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public final class Board {
    private static final Random random = new Random();
    private static final int[][] TRIPS = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    private final Mark[] board = new Mark[9];

    public static int getIndex(final int x, final int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IndexOutOfBoundsException("Coordinates should be from 1 to 3");
        }
        return x - 3 * y + 8;
    }

    public Board() {
        Arrays.fill(board, Mark.EMPTY);
    }

    public void set(int index, Mark state) {
        board[index] = state;
    }

    public boolean isEmpty() {
        return stream(board).allMatch(Mark.EMPTY::equals);
    }

    public boolean isEmpty(int index) {
        return board[index] == Mark.EMPTY;
    }

    public Optional<int[]> getTwoMarkTrips(Mark mark) {
        return stream(TRIPS).filter(i ->
                board[i[0]] == Mark.EMPTY && board[i[1]] == mark && board[i[2]] == mark
                        || board[i[1]] == Mark.EMPTY && board[i[0]] == mark && board[i[2]] == mark
                        || board[i[2]] == Mark.EMPTY && board[i[1]] == mark && board[i[0]] == mark)
                .findFirst();
    }

    public int getRandomFree() {
        final var freeCells = getFreeCells();
        return freeCells[random.nextInt(freeCells.length)];
    }

    public int[] getFreeCells() {
        return range(0, 9).filter(this::isEmpty).toArray();
    }

    public int getCellsCount(Mark symbol) {
        return (int) stream(board).filter(symbol::equals).count();
    }

    public int getTripsCount(Mark mark) {
        Predicate<int[]> threeInRow = line -> stream(line).filter(i -> board[i] == mark).count() == 3;
        return (int) stream(TRIPS).filter(threeInRow).count();
    }

    public State getState() {
        final var marks = Stream.of(Mark.X, Mark.O).mapToInt(this::getCellsCount).toArray();
        final var trips = Stream.of(Mark.X, Mark.O).mapToInt(this::getTripsCount).toArray();

        if (abs(marks[0] - marks[1]) > 1 || trips[0] > 0 && trips[1] > 0) {
            return State.IMPOSSIBLE;
        } else if (trips[0] > 0) {
            return State.X_WINS;
        } else if (trips[1] > 0) {
            return State.O_WINS;
        } else if (marks[0] + marks[1] == 9) {
            return State.DRAW;
        } else {
            return State.PLAYING;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "---------%n| %c %c %c |%n| %c %c %c |%n| %c %c %c |%n---------",
                stream(board).map(Mark::getSymbol).toArray());
    }
}