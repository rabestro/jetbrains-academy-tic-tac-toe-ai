package tictactoe.ai;

import tictactoe.CellState;
import tictactoe.TicTacToeBoard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player extends Ai {

    public Player(TicTacToeBoard board) {
        super(board, CellState.X);
    }

    @Override
    public int getMove() {
        final var scanner = new Scanner(System.in);
        int x, y;

        do {
            System.out.print("Enter the coordinates: ");
            try {
                x = scanner.nextInt();
                y = scanner.nextInt();
                if (notCorrectCoordinates(x, y)) {
                    System.out.println("Coordinates should be from 1 to 3");
                } else if (!board.isFree(x, y)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        } while (true);

        return 8 + x - 3 * y;
    }

    private boolean notCorrectCoordinates(int x, int y) {
        return x < 1 || x > 3 || y < 1 || y > 3;
    }

}
