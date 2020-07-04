package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Ai {

    public User(Board board, Mark mark) {
        super(board, mark);
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
                if (x < 1 || x > 3 || y < 1 || y > 3) {
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

//        scanner.close();
        return 8 + x - 3 * y;
    }

}
