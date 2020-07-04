package tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class TicTacToe {
    final static int MIN_COORDINATE = 1;
    final static int BOARD_SIZE = 3;
    final static char[] SYMBOLS = {'X', 'O'};
    final int[][] field = new int[BOARD_SIZE][BOARD_SIZE];
    private GameState state;
    private int currentPlayer;

    TicTacToe() {
        this("_________");
    }

    TicTacToe(final String field) {
        parseField(field);
    }

    private void parseField(String field) {
        int x = 0;
        int o = 0;
        for (int i = 0; i < field.length(); ++i) {
            char symbol = field.charAt(i);
            if (symbol == 'X') ++x;
            if (symbol == 'O') ++o;
            this.field[i / BOARD_SIZE][i % BOARD_SIZE] = symbol == '_' ? ' ' : symbol;
        }
        currentPlayer = x - o;
    }

    public boolean isOver() {
        return state != GameState.PLAYING;
    }

    public void askField() {
        final Scanner sc = new Scanner(System.in);
        System.out.print("Enter cells: ");
        final String field = sc.next();
        parseField(field);

    }

    void checkState() {
        final int[] count = new int[2];
        final int[] tripsCount = new int[2];

        for (int player = 0; player < 2; ++player) {
            final char symbol = SYMBOLS[player];
            count[player] = (int) Arrays.stream(field)
                    .flatMapToInt(x -> Arrays.stream(x))
                    .filter(i -> i == symbol)
                    .count();

            if (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol) {
                ++tripsCount[player];
            }
            if (field[0][2] == symbol && field[1][1] == symbol && field[2][0] == symbol) {
                ++tripsCount[player];
            }
            for (int i = 0; i < BOARD_SIZE; ++i) {
                if (field[i][0] == symbol && field[i][1] == symbol && field[i][2] == symbol) {
                    ++tripsCount[player];
                }
                if (field[0][i] == symbol && field[1][i] == symbol && field[2][i] == symbol) {
                    ++tripsCount[player];
                }
            }
        }

        if (Math.abs(count[0] - count[1]) > 1 || tripsCount[0] + tripsCount[1] > 1) {
            state = GameState.IMPOSSIBLE;
        } else if (tripsCount[0] == 1) {
            state = GameState.X_WINS;
        } else if (tripsCount[1] == 1) {
            state = GameState.O_WINS;
        } else if (count[0] + count[1] == 9) {
            state = GameState.DRAW;
        } else {
            state = GameState.PLAYING;
        }
    }


    public void printBoard() {
        System.out.println("---------");
        for (int[] row : field) {
            System.out.printf("| %c %c %c |%n", row[0], row[1], row[2]);
        }
        System.out.println("---------");
    }

    public void printMessage() {
        System.out.println(state.message);
    }

    public void makeMove(int x, int y) {
        this.field[BOARD_SIZE - y][x - 1] = SYMBOLS[currentPlayer];
    }

    private boolean isCellFree(int x, int y) {
        return field[BOARD_SIZE - y][x - 1] == ' ' || field[BOARD_SIZE - y][x - 1] == '_';
    }

    private boolean isCorrectCoordinates(int x, int y) {
        return x > 0 && x <= BOARD_SIZE && y > 0 && y <= BOARD_SIZE;
    }

    public void askUser() {
        do {
            final Scanner sc = new Scanner(System.in);
            System.out.print("Enter the coordinates: ");
            try {
                final int x = sc.nextInt();
                final int y = sc.nextInt();
                if (!isCorrectCoordinates(x, y)) {
                    System.out.printf("Coordinates should be from %d to %d!%n", MIN_COORDINATE, BOARD_SIZE);
                } else if (!isCellFree(x, y)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    makeMove(x, y);
                    currentPlayer = 1 - currentPlayer;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        } while (true);
        //sc.close();
    }
}
