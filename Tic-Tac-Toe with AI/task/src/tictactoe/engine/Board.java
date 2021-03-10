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

    /**
     * The function checks and converts coordinates from user friendly view
     * to the internal index for one-dimensional array.
     * <p>
     * The bottom left cell has the coordinates (1, 1) and the top right
     * cell has the coordinates (3, 3) like in this table:
     * <p>
     * (1, 3) (2, 3) (3, 3)
     * (1, 2) (2, 2) (3, 2)
     * (1, 1) (2, 1) (3, 1)
     *
     * @param x - the column is number from 1 to 3
     * @param y - the row is number from 1 to 3
     * @return index for internal representation of tic-tac-toe board
     */
    public static int getIndex(final int x, final int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IndexOutOfBoundsException("Coordinates should be from 1 to 3");
        }
        return x - 3 * y + 8;
    }

    public Board() {
        Arrays.fill(board, Mark.EMPTY);
    }

    /**
     * The method marks the cell with the given index by the given mark
     *
     * @param index of the cell to mark
     * @param state the mark X, O or EMPTY
     */
    public void set(int index, Mark state) {
        board[index] = state;
    }

    /**
     * The function checks is the board has no any marks
     *
     * @return true is the board is empty
     */
    public boolean isEmpty() {
        return stream(board).allMatch(Mark.EMPTY::equals);
    }

    /**
     * The function checks is the cell has no mark
     *
     * @param index of the cell to check
     * @return true if cell is EMPTY
     */
    public boolean isEmpty(int index) {
        return board[index] == Mark.EMPTY;
    }

    /**
     * The function scans all possible lines (trips) on the board for the line contains
     * two the same marks and one empty field. If found returns that line.
     *
     * @param mark the mark (X or O) for search.
     * @return Optional int[3] represent the line.
     */
    public Optional<int[]> getLineWithTwoMarks(Mark mark) {
        final Predicate<int[]> twoMarks = cells ->
                board[cells[0]] == Mark.EMPTY && board[cells[1]] == mark && board[cells[2]] == mark
                        || board[cells[1]] == Mark.EMPTY && board[cells[0]] == mark && board[cells[2]] == mark
                        || board[cells[2]] == Mark.EMPTY && board[cells[1]] == mark && board[cells[0]] == mark;

        return stream(TRIPS).filter(twoMarks).findFirst();
    }

    /**
     * The function randomly select one free cell
     *
     * @return random index of free cell
     */
    public int getRandomFree() {
        final var freeCells = getFreeCells();
        return freeCells[random.nextInt(freeCells.length)];
    }

    /**
     * The functions return array of free cell indexes
     *
     * @return int array contains indexes of free cells
     */
    public int[] getFreeCells() {
        return range(0, 9).filter(this::isEmpty).toArray();
    }

    /**
     * The function calculates count of cells with given mark
     *
     * @param mark to look for
     * @return count of cells
     */
    public int getCellsCount(Mark mark) {
        return (int) stream(board).filter(mark::equals).count();
    }

    /**
     * The function calculates the number of lines with three marks
     *
     * @param mark to check
     * @return count of lines with three given marks
     */
    public int getTripsCount(Mark mark) {
        Predicate<int[]> threeInRow = line -> stream(line).allMatch(i -> board[i] == mark);

        return (int) stream(TRIPS).filter(threeInRow).count();
    }

    /**
     * The function returns state of the game
     *
     * @return enum State represents state of the game
     */
    public State getGameState() {
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
        return String.format("---------%n| %c %c %c |%n| %c %c %c |%n| %c %c %c |%n---------",
                stream(board).map(Mark::getMark).toArray());
    }
}