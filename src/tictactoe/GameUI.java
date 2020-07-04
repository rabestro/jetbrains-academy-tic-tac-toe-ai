package tictactoe;

import tictactoe.ai.Ai;
import tictactoe.ai.Easy;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class GameUI {
    private TicTacToeBoard board;
    private Scanner scanner;
    private Ai ai;

    public GameUI(Scanner scanner) {
        this.scanner = scanner;
        ai = new Easy();
    }

    void run() {
        board = new TicTacToeBoard();
        System.out.println(board);
        askUser();
        System.out.println(board);
        System.out.println("Making move level \"easy\"");
        board.set(ai.getMove(board), CellState.COMPUTER);
        System.out.println(board);
    }

    public void askUser() {
        do {
            System.out.print("Enter the coordinates: ");
            try {
                final int x = scanner.nextInt();
                final int y = scanner.nextInt();
                if (notCorrectCoordinates(x, y)) {
                    System.out.println("Coordinates should be from 1 to 3");
                } else if (!board.isFree(x, y)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    board.set(x, y, CellState.PLAYER);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        } while (true);
    }

    private boolean notCorrectCoordinates(int x, int y) {
        return x < 1 || x > 3 || y < 1 || y > 3;
    }
}
